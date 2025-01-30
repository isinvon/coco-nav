package com.ruoyi.project.admin.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.admin.service.custom.BookmarkCustomService;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.project.admin.mapper.BookmarkMapper;
import com.ruoyi.project.admin.domain.Bookmark;
import org.springframework.stereotype.Service;

/**
 * 书签管理Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class BookmarkServiceImpl implements BookmarkCustomService
{
    @Autowired
    private BookmarkMapper bookmarkMapper;

    /**
     * 查询书签管理
     * 
     * @param bookmarkId 书签管理主键
     * @return 书签管理
     */
    @Override
    public Bookmark selectBookmarkByBookmarkId(Long bookmarkId)
    {
        return bookmarkMapper.selectBookmarkByBookmarkId(bookmarkId);
    }

    /**
     * 查询书签管理列表
     * 
     * @param bookmark 书签管理
     * @return 书签管理
     */
    @Override
    public List<Bookmark> selectBookmarkList(Bookmark bookmark)
    {
        return bookmarkMapper.selectBookmarkList(bookmark);
    }

    /**
     * 新增书签管理
     * 
     * @param bookmark 书签管理
     * @return 结果
     */
    @Override
    public int insertBookmark(Bookmark bookmark)
    {
        bookmark.setCreateTime(DateUtils.getNowDate());
        return bookmarkMapper.insertBookmark(bookmark);
    }

    /**
     * 修改书签管理
     * 
     * @param bookmark 书签管理
     * @return 结果
     */
    @Override
    public int updateBookmark(Bookmark bookmark)
    {
        bookmark.setUpdateTime(DateUtils.getNowDate());
        return bookmarkMapper.updateBookmark(bookmark);
    }

    /**
     * 批量删除书签管理
     * 
     * @param bookmarkIds 需要删除的书签管理主键
     * @return 结果
     */
    @Override
    public int deleteBookmarkByBookmarkIds(Long[] bookmarkIds)
    {
        return bookmarkMapper.deleteBookmarkByBookmarkIds(bookmarkIds);
    }

    /**
     * 删除书签管理信息
     * 
     * @param bookmarkId 书签管理主键
     * @return 结果
     */
    @Override
    public int deleteBookmarkByBookmarkId(Long bookmarkId)
    {
        return bookmarkMapper.deleteBookmarkByBookmarkId(bookmarkId);
    }
}
