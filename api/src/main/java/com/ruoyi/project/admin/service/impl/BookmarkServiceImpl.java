package com.ruoyi.project.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.crawler.CrawlerUtil;
import com.ruoyi.project.admin.domain.po.Bookmark;
import com.ruoyi.project.admin.domain.po.BookmarkLog;
import com.ruoyi.project.admin.mapper.BookmarkMapper;
import com.ruoyi.project.admin.service.BookmarkLogService;
import com.ruoyi.project.admin.service.BookmarkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<Bookmark> getBookmarkListByQueryCondition(Bookmark bookmark) {

        LambdaQueryWrapper<Bookmark> qw = new LambdaQueryWrapper<>();

        // 根据书签标题
        if (bookmark.getTitle() != null) {
            qw.like(Bookmark::getTitle, bookmark.getTitle());
        }

        // 根据 url
        if (bookmark.getUrl() != null) {
            qw.like(Bookmark::getUrl, bookmark.getUrl());
        }

        // 根据状态
        if (bookmark.getStatus() != null) {
            qw.eq(Bookmark::getStatus, bookmark.getStatus());
        }

        // 根据删除状态
        if (bookmark.getIsDeleted() != null) {
            qw.eq(Bookmark::getIsDeleted, bookmark.getIsDeleted());
        }

        return list(qw);
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
            details.append("书签信息: ");
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
    public Boolean updateBookmark(Bookmark bookmark) {
        // 根据传入的书签ID查询数据库中原始记录
        Bookmark oldBookmark = this.getById(bookmark.getId());
        if (oldBookmark == null) {
            // 如果旧记录不存在，可根据实际需求抛异常或者返回 false
            log.error("更新失败，书签ID {} 对应的记录不存在", bookmark.getId());
            return false;
        }

        // 使用 StringBuilder 拼接所有修改的详细信息
        StringBuilder details = new StringBuilder();

        // 对比各个字段（仅比较传入新值不为 null 的字段）
        if (bookmark.getTitle() != null && !bookmark.getTitle().equals(oldBookmark.getTitle())) {
            details.append("修改书签标题从: ")
                    .append("【")
                    .append(oldBookmark.getTitle())
                    .append("】")
                    .append(" 修改为: ")
                    .append("【")
                    .append(bookmark.getTitle())
                    .append("】")
            ;
        }
        if (bookmark.getUrl() != null && !bookmark.getUrl().equals(oldBookmark.getUrl())) {
            details.append("修改书签地址从: ")
                    .append("【")
                    .append(oldBookmark.getUrl())
                    .append("】")
                    .append(" 修改为: ")
                    .append("【")
                    .append(bookmark.getUrl())
                    .append("】")
            ;
        }
        if (bookmark.getDescription() != null && !bookmark.getDescription().equals(oldBookmark.getDescription())) {
            details.append("修改书签描述从: ")
                    .append(oldBookmark.getDescription())
                    .append("】")
                    .append(" 修改为: ")
                    .append("【")
                    .append(bookmark.getDescription())
                    .append("】")
            ;
        }
        if (bookmark.getIcon() != null && !bookmark.getIcon().equals(oldBookmark.getIcon())) {
            details.append("修改书签图标从: ")
                    .append("【")
                    .append(oldBookmark.getIcon())
                    .append("】")
                    .append(" 修改为: ")
                    .append("【")
                    .append(bookmark.getIcon())
                    .append("】")
            ;
        }
        if (bookmark.getBookmarkCategoryId() != null && !bookmark.getBookmarkCategoryId().equals(oldBookmark.getBookmarkCategoryId())) {
            details.append("修改书签分类ID从: ")
                    .append("【")
                    .append(oldBookmark.getBookmarkCategoryId())
                    .append("】")
                    .append(" 修改为: ")
                    .append("【")
                    .append(bookmark.getBookmarkCategoryId())
                    .append("】")
            ;
        }
        if (bookmark.getClickCount() != null && !bookmark.getClickCount().equals(oldBookmark.getClickCount())) {
            details.append("修改点击次数从: ")
                    .append("【")
                    .append(oldBookmark.getClickCount())
                    .append("】")
                    .append(" 修改为: ")
                    .append("【")
                    .append(bookmark.getClickCount())
                    .append("】")
            ;
        }
        if (bookmark.getSortOrder() != null && !bookmark.getSortOrder().equals(oldBookmark.getSortOrder())) {
            details.append("修改排序值从: ")
                    .append("【")
                    .append(oldBookmark.getSortOrder())
                    .append("】")
                    .append(" 修改为: ")
                    .append("【")
                    .append(bookmark.getSortOrder())
                    .append("】")
            ;
        }
        if (bookmark.getStatus() != null && !bookmark.getStatus().equals(oldBookmark.getStatus())) {
            details.append("修改状态从: ")
                    .append("【")
                    .append(oldBookmark.getStatus())
                    .append("】")
                    .append(" 修改为: ")
                    .append("【")
                    .append(bookmark.getStatus())
                    .append("】")
            ;
        }

        // 执行更新操作
        boolean update = updateById(bookmark);
        if (update) {
            // 更新成功，记录书签操作日志
            BookmarkLog bookmarkLog = new BookmarkLog();
            bookmarkLog.setAction(BookmarkLog.BOOKMARK_LOG_ACTION_EDIT);
            bookmarkLog.setBookmarkId(bookmark.getId());
            bookmarkLog.setOperatorId(SecurityUtils.getUserId());
            bookmarkLog.setOperatorName(SecurityUtils.getUsername());

            // 将拼接好的详细信息记录到日志中
            bookmarkLog.setActionDetails(details.toString());

            // 保存日志记录
            bookmarkLogService.save(bookmarkLog);
        }

        return update;
    }

    @Override
    public Boolean deleteBookmark(List<Long> idList) {

        boolean remove = removeByIds(idList);

        if (remove) {
            // 删除成功, 记录书签操作日志
            BookmarkLog bookmarkLog = new BookmarkLog();
            bookmarkLog.setAction(BookmarkLog.BOOKMARK_LOG_ACTION_DELETE);
            bookmarkLog.setBookmarkId(idList.get(0));
            bookmarkLog.setOperatorId(SecurityUtils.getUserId());
            bookmarkLog.setOperatorName(SecurityUtils.getUsername());
            bookmarkLogService.save(bookmarkLog);
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
}
