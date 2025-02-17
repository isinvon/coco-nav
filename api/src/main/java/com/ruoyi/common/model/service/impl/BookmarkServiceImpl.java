package com.ruoyi.common.model.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.crawler.CrawlerUtil;
import com.ruoyi.common.model.domain.po.Bookmark;
import com.ruoyi.common.model.domain.po.BookmarkLog;
import com.ruoyi.common.model.domain.po.BookmarkTag;
import com.ruoyi.common.model.domain.po.BookmarkTagRelation;
import com.ruoyi.common.model.domain.vo.BookmarkVo;
import com.ruoyi.common.model.mapper.BookmarkMapper;
import com.ruoyi.common.model.service.BookmarkLogService;
import com.ruoyi.common.model.service.BookmarkService;
import com.ruoyi.common.model.service.BookmarkTagRelationService;
import com.ruoyi.common.model.service.BookmarkTagService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 书签管理Service业务层处理
 *
 * @author sinvon
 * @date 2025-01-30
 */
@Slf4j
@Service
public class BookmarkServiceImpl extends ServiceImpl<BookmarkMapper, Bookmark> implements BookmarkService {

    @Resource
    private BookmarkLogService bookmarkLogService;

    @Resource
    private BookmarkTagRelationService bookmarkTagRelationService;

    @Resource
    private BookmarkTagService bookmarkTagService;

    @Override
    public List<BookmarkVo> getBookmarkListByQueryCondition(BookmarkVo bookmarkVo) {
        // 1. 构建查询条件
        LambdaQueryWrapper<Bookmark> qw = new LambdaQueryWrapper<>();

        if (bookmarkVo.getTitle() != null) {
            qw.like(Bookmark::getTitle, bookmarkVo.getTitle());
        }
        if (bookmarkVo.getUrl() != null) {
            qw.like(Bookmark::getUrl, bookmarkVo.getUrl());
        }
        if (bookmarkVo.getStatus() != null) {
            qw.eq(Bookmark::getStatus, bookmarkVo.getStatus());
        }
        if (bookmarkVo.getIsDeleted() != null) {
            qw.eq(Bookmark::getIsDeleted, bookmarkVo.getIsDeleted());
        }

        // 条件: 1. bookmarkTags 存在且不为空,不为空字符串时, 才进行条件拼接 (使用 Apache Commons / Spring 工具类)
        if (CollectionUtils.isNotEmpty(bookmarkVo.getBookmarkTags()) && StringUtils.isNotBlank(bookmarkVo.getBookmarkTags().get(0).getTagName())){
            // 直接通过标签名称批量查询关联的书签ID (替代原有两次查询)
            List<String> tagNames = bookmarkVo.getBookmarkTags().stream()
                    .map(BookmarkTag::getTagName)
                    .collect(Collectors.toList());

            List<Long> bookmarkIds = bookmarkTagRelationService
                    .list(new LambdaQueryWrapper<BookmarkTagRelation>()
                            .in(BookmarkTagRelation::getBookmarkTagId,
                                    bookmarkTagService.list(new LambdaQueryWrapper<BookmarkTag>()
                                                    .in(BookmarkTag::getTagName, tagNames))
                                            .stream().map(BookmarkTag::getId).toList()
                            ))
                    .stream()
                    .map(BookmarkTagRelation::getBookmarkId)
                    .distinct()
                    .collect(Collectors.toList());

            if (!bookmarkIds.isEmpty()) { // 仅当ID列表非空时添加条件
                qw.in(Bookmark::getId, bookmarkIds);
            }else {
                return Collections.emptyList();
            }
        }

        // 2. 查询书签列表
        List<Bookmark> bookmarkList = list(qw);
        if (bookmarkList.isEmpty()) {
            return Collections.emptyList();
        }

        // 3. 提取书签ID列表
        List<Long> bookmarkIds = bookmarkList.stream().map(Bookmark::getId).toList();

        // 4. 查询所有书签对应的标签关系
        LambdaQueryWrapper<BookmarkTagRelation> relationQuery = new LambdaQueryWrapper<>();
        relationQuery.in(BookmarkTagRelation::getBookmarkId, bookmarkIds);

        List<BookmarkTagRelation> relations = bookmarkTagRelationService.list(relationQuery);
        if (relations.isEmpty()) {
            return bookmarkList.stream().map(bookmark -> {
                BookmarkVo vo = new BookmarkVo();
                BeanUtil.copyProperties(bookmark, vo);
                vo.setBookmarkTags(Collections.emptyList());
                return vo;
            }).toList();
        }

        // 5. 获取所有的标签ID
        List<Long> tagIds = relations.stream().map(BookmarkTagRelation::getBookmarkTagId).distinct().toList();

        // 6. 查询所有标签
        List<BookmarkTag> tags = bookmarkTagService.listByIds(tagIds);

        // 7. 组装数据
        return bookmarkList.stream().map(bookmark -> {
            BookmarkVo vo = new BookmarkVo();
            BeanUtil.copyProperties(bookmark, vo);

            // 关联标签
            List<Long> relatedTagIds = relations.stream()
                    .filter(r -> r.getBookmarkId().equals(bookmark.getId()))
                    .map(BookmarkTagRelation::getBookmarkTagId)
                    .toList();

            vo.setBookmarkTags(tags.stream().filter(tag -> relatedTagIds.contains(tag.getId())).toList());
            return vo;
        }).toList();
    }

    @Override
    public Boolean addBookmark(Bookmark bookmark) {
        // 执行添加操作
        boolean save = save(bookmark);

        if (save) {
            // 添加成功, 记录书签操作日志
            BookmarkLog bookmarkLog = new BookmarkLog();
            bookmarkLog.setAction(BookmarkLog.BOOKMARK_LOG_ACTION_ADD);
            bookmarkLog.setBookmarkId(bookmark.getId());
            bookmarkLog.setOperatorId(SecurityUtils.getUserId());
            bookmarkLog.setOperatorName(SecurityUtils.getUsername());

            // 使用 StringBuilder 拼接所有字段信息
            StringBuilder details = new StringBuilder();
            details.append("添加书签信息: ");
            details.append("标题【").append(bookmark.getTitle()).append("】, ");
            details.append("地址【").append(bookmark.getUrl()).append("】, ");
            details.append("描述【").append(bookmark.getDescription()).append("】, ");
            details.append("分类ID【").append(bookmark.getBookmarkCategoryId()).append("】, ");
            details.append("图标【").append(bookmark.getIcon()).append("】, ");
            details.append("点击次数【").append(bookmark.getClickCount()).append("】, ");
            details.append("排序值【").append(bookmark.getSortOrder()).append("】, ");
            details.append("状态【").append(bookmark.getStatus()).append("】");

            // 如果需要记录其他字段（例如创建时间、更新时间）可继续拼接
            // details.append(" 创建时间【").append(bookmark.getCreateTime()).append("】, ");
            // details.append(" 更新时间【").append(bookmark.getUpdateTime()).append("】");

            // 将拼接好的详细信息记录到日志对象中
            bookmarkLog.setActionDetails(details.toString());

            // 保存日志记录
            bookmarkLogService.save(bookmarkLog);
        }

        return save;
    }

    @Override
    public Boolean updateBookmark(BookmarkVo bookmarkVo) {
        Bookmark newBookmark = new Bookmark();
        // 拷贝属性
        BeanUtil.copyProperties(bookmarkVo, newBookmark);

        // 查询原始书签信息
        Bookmark oldBookmark = this.getById(newBookmark.getId());
        if (oldBookmark == null) {
            log.error("更新失败，书签ID {} 对应的记录不存在", newBookmark.getId());
            return false;
        }

        // 记录修改详情
        StringBuilder details = new StringBuilder();

        // 对比各字段
        if (newBookmark.getTitle() != null && !newBookmark.getTitle().equals(oldBookmark.getTitle())) {
            details.append("修改书签标题从: 【").append(oldBookmark.getTitle()).append("】 修改为: 【").append(newBookmark.getTitle()).append("】 ");
        }
        if (newBookmark.getUrl() != null && !newBookmark.getUrl().equals(oldBookmark.getUrl())) {
            details.append("修改书签地址从: 【").append(oldBookmark.getUrl()).append("】 修改为: 【").append(newBookmark.getUrl()).append("】 ");
        }
        if (newBookmark.getDescription() != null && !newBookmark.getDescription().equals(oldBookmark.getDescription())) {
            details.append("修改书签描述从: 【").append(oldBookmark.getDescription()).append("】 修改为: 【").append(newBookmark.getDescription()).append("】 ");
        }
        if (newBookmark.getIcon() != null && !newBookmark.getIcon().equals(oldBookmark.getIcon())) {
            details.append("修改书签图标从: 【").append(oldBookmark.getIcon()).append("】 修改为: 【").append(newBookmark.getIcon()).append("】 ");
        }
        if (newBookmark.getBookmarkCategoryId() != null && !newBookmark.getBookmarkCategoryId().equals(oldBookmark.getBookmarkCategoryId())) {
            details.append("修改书签分类ID从: 【").append(oldBookmark.getBookmarkCategoryId()).append("】 修改为: 【").append(newBookmark.getBookmarkCategoryId()).append("】 ");
        }
        if (newBookmark.getClickCount() != null && !newBookmark.getClickCount().equals(oldBookmark.getClickCount())) {
            details.append("修改点击次数从: 【").append(oldBookmark.getClickCount()).append("】 修改为: 【").append(newBookmark.getClickCount()).append("】 ");
        }
        if (newBookmark.getSortOrder() != null && !newBookmark.getSortOrder().equals(oldBookmark.getSortOrder())) {
            details.append("修改排序值从: 【").append(oldBookmark.getSortOrder()).append("】 修改为: 【").append(newBookmark.getSortOrder()).append("】 ");
        }
        if (newBookmark.getStatus() != null && !newBookmark.getStatus().equals(oldBookmark.getStatus())) {
            details.append("修改状态从: 【").append(oldBookmark.getStatus()).append("】 修改为: 【").append(newBookmark.getStatus()).append("】 ");
        }

        // 标签逻辑 ======================================== start ============================================================================
        // 查询当前书签的旧标签关系
        LambdaQueryWrapper<BookmarkTagRelation> relationLambdaQueryWrapper = new LambdaQueryWrapper<BookmarkTagRelation>()
                .eq(BookmarkTagRelation::getBookmarkId, newBookmark.getId());
        List<BookmarkTagRelation> oldRelations = bookmarkTagRelationService.list(relationLambdaQueryWrapper);

        // 处理前端传下来的标签
        List<BookmarkTag> newTags = bookmarkVo.getBookmarkTags();

        if (newTags != null) {
            // 获取现有标签的名称集合
            Map<String, BookmarkTagRelation> existingTagMap = oldRelations.stream()
                    .collect(Collectors.toMap(
                            relation -> bookmarkTagService.getById(relation.getBookmarkTagId()).getTagName(),
                            relation -> relation
                    ));

            // 获取新标签的名称集合
            Set<String> newTagNames = newTags.stream()
                    .map(BookmarkTag::getTagName)
                    .collect(Collectors.toSet());

            // 找出需要删除的标签
            Set<String> tagsToDelete = new HashSet<>(existingTagMap.keySet());
            tagsToDelete.removeAll(newTagNames);

            // 找出需要新增的标签
            Set<String> tagsToAdd = new HashSet<>(newTagNames);
            tagsToAdd.removeAll(existingTagMap.keySet());

            // 删除需要删除的标签关系
            for (String tagName : tagsToDelete) {
                BookmarkTagRelation relationToDelete = existingTagMap.get(tagName);
                bookmarkTagRelationService.removeById(relationToDelete.getId());
                details.append("删除标签【").append(tagName).append("】 ");
            }

            // [增] 遍历tagsToAdd，如果标签在BookmarkTag中不存在，则新建标签并插入BookmarkTagRelation中
            for (String tagName : tagsToAdd) {
                BookmarkTag existingTag = bookmarkTagService.getOne(new LambdaQueryWrapper<BookmarkTag>().eq(BookmarkTag::getTagName, tagName));
                if (existingTag == null) {
                    // 如果不存在，则在BookmarkTag中新建标签
                    BookmarkTag newBookmarkTag = new BookmarkTag();
                    newBookmarkTag.setTagName(tagName);
                    boolean bookmarkTagSaveFlag = bookmarkTagService.save(newBookmarkTag);

                    if (bookmarkTagSaveFlag) {
                        // 记录标签日志
                        details.append("新增标签【").append(newBookmarkTag.getTagName()).append("】 ");
                        // 设置标签ID到新建标签对象中
                        newBookmarkTag.setId(bookmarkTagService.getOne(new LambdaQueryWrapper<BookmarkTag>().eq(BookmarkTag::getTagName, tagName)).getId());

                        // 插入BookmarkTagRelation
                        BookmarkTagRelation bookmarkTagRelation = new BookmarkTagRelation();
                        bookmarkTagRelation.setBookmarkId(newBookmark.getId()); // 设置书签ID
                        bookmarkTagRelation.setBookmarkTagId(newBookmarkTag.getId()); // 设置标签ID
                        bookmarkTagRelationService.save(bookmarkTagRelation);
                    }
                } else {
                    // 如果标签已存在，直接插入BookmarkTagRelation
                    BookmarkTagRelation bookmarkTagRelation = new BookmarkTagRelation();
                    bookmarkTagRelation.setBookmarkId(newBookmark.getId()); // 设置书签ID
                    bookmarkTagRelation.setBookmarkTagId(existingTag.getId()); // 设置标签ID
                    bookmarkTagRelationService.save(bookmarkTagRelation);
                    details.append("新增标签【").append(existingTag.getTagName()).append("】 ");
                }
            }
        }

        // 标签逻辑 ======================================== end ============================================================================

        // 执行更新操作
        boolean update = updateById(newBookmark);
        if (update) {
            // 若details有内容，则记录到BookmarkLog中
            if (!details.isEmpty()) {
                BookmarkLog bookmarkLog = new BookmarkLog();
                bookmarkLog.setAction(BookmarkLog.BOOKMARK_LOG_ACTION_EDIT);
                bookmarkLog.setBookmarkId(newBookmark.getId());
                bookmarkLog.setOperatorId(SecurityUtils.getUserId());
                bookmarkLog.setOperatorName(SecurityUtils.getUsername());
                bookmarkLog.setActionDetails(String.format("书签ID [%d] %s", newBookmark.getId(), details));
                // 保存日志
                bookmarkLogService.save(bookmarkLog);
            }
        }

        return update;
    }

    @Override
    public Boolean deleteBookmark(List<Long> idList) {
        // 先查询待删除的书签信息（便于记录日志）
        List<Bookmark> bookmarksToDelete = listByIds(idList);

        // 执行删除操作
        boolean remove = removeByIds(idList);

        if (remove) {
            // 删除成功，记录每个书签的删除日志
            for (Bookmark bookmark : bookmarksToDelete) {
                BookmarkLog bookmarkLog = new BookmarkLog();
                bookmarkLog.setAction(BookmarkLog.BOOKMARK_LOG_ACTION_DELETE);
                bookmarkLog.setBookmarkId(bookmark.getId());
                bookmarkLog.setOperatorId(SecurityUtils.getUserId());
                bookmarkLog.setOperatorName(SecurityUtils.getUsername());

                // 使用 StringBuilder 拼接删除的详细信息
                StringBuilder details = new StringBuilder();
                details.append("删除书签信息: ");
                details.append("标题【").append(bookmark.getTitle()).append("】, ");
                details.append("地址【").append(bookmark.getUrl()).append("】, ");
                details.append("描述【").append(bookmark.getDescription()).append("】, ");
                details.append("分类ID【").append(bookmark.getBookmarkCategoryId()).append("】, ");
                details.append("图标【").append(bookmark.getIcon()).append("】, ");
                details.append("点击次数【").append(bookmark.getClickCount()).append("】, ");
                details.append("排序值【").append(bookmark.getSortOrder()).append("】, ");
                details.append("状态【").append(bookmark.getStatus()).append("】");

                // 将拼接好的详细信息记录到日志对象中
                bookmarkLog.setActionDetails(details.toString());

                // 保存日志记录
                bookmarkLogService.save(bookmarkLog);
            }
        }
        return remove;
    }


    @Override
    public Map<String, String> getUrlInfoByCrawler(String url) {
        Map<String, String> map = null;
        try {
            map = CrawlerUtil.urlCrawl(url);
        } catch (Exception e) {
            log.info("网络请求失败或解析异常: {}", e.getMessage());
        }
        return map;
    }

    @Override
    public BookmarkVo getBookmarkById(Long bookmarkId) {
        // 1. 查询书签
        Bookmark bookmark = getById(bookmarkId);
        if (bookmark == null) {
            return null;
        }

        // 2. 查询书签关联的标签关系
        LambdaQueryWrapper<BookmarkTagRelation> relationQuery = new LambdaQueryWrapper<>();
        relationQuery.eq(BookmarkTagRelation::getBookmarkId, bookmarkId);
        List<BookmarkTagRelation> relations = bookmarkTagRelationService.list(relationQuery);

        // 3. 提取标签 ID
        List<Long> tagIds = relations.stream().map(BookmarkTagRelation::getBookmarkTagId).toList();

        // 4. 查询标签信息
        List<BookmarkTag> tags = tagIds.isEmpty() ? Collections.emptyList() : bookmarkTagService.listByIds(tagIds);

        // 5. 封装 BookmarkVo
        BookmarkVo bookmarkVo = new BookmarkVo();
        BeanUtil.copyProperties(bookmark, bookmarkVo);
        bookmarkVo.setBookmarkTags(tags);

        return bookmarkVo;
    }
}
