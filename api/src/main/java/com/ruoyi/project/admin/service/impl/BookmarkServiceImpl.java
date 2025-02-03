package com.ruoyi.project.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.crawler.CrawlerUtil;
import com.ruoyi.project.admin.domain.po.Bookmark;
import com.ruoyi.project.admin.mapper.BookmarkMapper;
import com.ruoyi.project.admin.service.BookmarkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

        return save;
    }

    @Override
    public Boolean updateBookmark(Bookmark bookmark) {

        boolean update = updateById(bookmark);

        return update;
    }

    @Override
    public Boolean deleteBookmark(List<Long> idList) {

        boolean remove = removeByIds(idList);

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
