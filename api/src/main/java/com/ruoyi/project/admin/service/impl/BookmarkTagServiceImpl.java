package com.ruoyi.project.admin.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.admin.service.custom.BookmarkTagCustomService;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.project.admin.mapper.BookmarkTagMapper;
import com.ruoyi.project.admin.domain.BookmarkTag;
import org.springframework.stereotype.Service;

/**
 * 书签标签Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class BookmarkTagServiceImpl implements BookmarkTagCustomService
{
    @Autowired
    private BookmarkTagMapper bookmarkTagMapper;

    /**
     * 查询书签标签
     * 
     * @param bookmarkTagId 书签标签主键
     * @return 书签标签
     */
    @Override
    public BookmarkTag selectBookmarkTagByBookmarkTagId(Long bookmarkTagId)
    {
        return bookmarkTagMapper.selectBookmarkTagByBookmarkTagId(bookmarkTagId);
    }

    /**
     * 查询书签标签列表
     * 
     * @param bookmarkTag 书签标签
     * @return 书签标签
     */
    @Override
    public List<BookmarkTag> selectBookmarkTagList(BookmarkTag bookmarkTag)
    {
        return bookmarkTagMapper.selectBookmarkTagList(bookmarkTag);
    }

    /**
     * 新增书签标签
     * 
     * @param bookmarkTag 书签标签
     * @return 结果
     */
    @Override
    public int insertBookmarkTag(BookmarkTag bookmarkTag)
    {
        bookmarkTag.setCreateTime(DateUtils.getNowDate());
        return bookmarkTagMapper.insertBookmarkTag(bookmarkTag);
    }

    /**
     * 修改书签标签
     * 
     * @param bookmarkTag 书签标签
     * @return 结果
     */
    @Override
    public int updateBookmarkTag(BookmarkTag bookmarkTag)
    {
        bookmarkTag.setUpdateTime(DateUtils.getNowDate());
        return bookmarkTagMapper.updateBookmarkTag(bookmarkTag);
    }

    /**
     * 批量删除书签标签
     * 
     * @param bookmarkTagIds 需要删除的书签标签主键
     * @return 结果
     */
    @Override
    public int deleteBookmarkTagByBookmarkTagIds(Long[] bookmarkTagIds)
    {
        return bookmarkTagMapper.deleteBookmarkTagByBookmarkTagIds(bookmarkTagIds);
    }

    /**
     * 删除书签标签信息
     * 
     * @param bookmarkTagId 书签标签主键
     * @return 结果
     */
    @Override
    public int deleteBookmarkTagByBookmarkTagId(Long bookmarkTagId)
    {
        return bookmarkTagMapper.deleteBookmarkTagByBookmarkTagId(bookmarkTagId);
    }
}
