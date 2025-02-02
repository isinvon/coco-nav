package com.ruoyi.project.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.admin.domain.po.Bookmark;
import com.ruoyi.project.admin.mapper.BookmarkMapper;
import com.ruoyi.project.admin.service.BookmarkService;
import com.ruoyi.project.admin.service.custom.BookmarkCustomService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 书签管理Service业务层处理
 *
 * @author sinvon
 * @date 2025-01-30
 */
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
}
