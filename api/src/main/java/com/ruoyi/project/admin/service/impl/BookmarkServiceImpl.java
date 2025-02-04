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

        boolean save = save(bookmark);

        if (save) {
            // 添加成功, 记录书签操作日志
            BookmarkLog bookmarkLog = new BookmarkLog();
            bookmarkLog.setAction(BookmarkLog.BOOKMARK_LOG_ACTION_ADD);
            bookmarkLog.setBookmarkId(bookmark.getId());
            bookmarkLog.setOperatorId(SecurityUtils.getUserId());
            bookmarkLog.setOperatorName(SecurityUtils.getUsername());
            bookmarkLogService.save(bookmarkLog);
        }

        return save;
    }

    @Override
    public Boolean updateBookmark(Bookmark bookmark) {

        boolean update = updateById(bookmark);

        if (update) {
            // 修改成功, 记录书签操作日志
            BookmarkLog bookmarkLog = new BookmarkLog();
            bookmarkLog.setAction(BookmarkLog.BOOKMARK_LOG_ACTION_EDIT);
            bookmarkLog.setBookmarkId(bookmark.getId());
            bookmarkLog.setOperatorId(SecurityUtils.getUserId());
            bookmarkLog.setOperatorName(SecurityUtils.getUsername());
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
