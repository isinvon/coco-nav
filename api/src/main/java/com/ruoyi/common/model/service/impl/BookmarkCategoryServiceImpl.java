package com.ruoyi.common.model.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.model.service.BookmarkCategoryService;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.model.service.custom.BookmarkCategoryCustomService;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.common.model.mapper.BookmarkCategoryMapper;
import com.ruoyi.common.model.domain.po.BookmarkCategory;
import org.springframework.stereotype.Service;

/**
 * 书签分类Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class BookmarkCategoryServiceImpl extends ServiceImpl<BookmarkCategoryMapper,BookmarkCategory> implements BookmarkCategoryCustomService, BookmarkCategoryService
{
    @Autowired
    private BookmarkCategoryMapper bookmarkCategoryMapper;

    /**
     * 查询书签分类
     * 
     * @param bookmarkCategoryId 书签分类主键
     * @return 书签分类
     */
    @Override
    public BookmarkCategory selectBookmarkCategoryByBookmarkCategoryId(Long bookmarkCategoryId)
    {
        return bookmarkCategoryMapper.selectBookmarkCategoryByBookmarkCategoryId(bookmarkCategoryId);
    }

    /**
     * 查询书签分类列表
     * 
     * @param bookmarkCategory 书签分类
     * @return 书签分类
     */
    @Override
    public List<BookmarkCategory> selectBookmarkCategoryList(BookmarkCategory bookmarkCategory)
    {
        return bookmarkCategoryMapper.selectBookmarkCategoryList(bookmarkCategory);
    }

    /**
     * 新增书签分类
     * 
     * @param bookmarkCategory 书签分类
     * @return 结果
     */
    @Override
    public int insertBookmarkCategory(BookmarkCategory bookmarkCategory)
    {
        bookmarkCategory.setCreateTime(DateUtils.getNowDate());
        return bookmarkCategoryMapper.insertBookmarkCategory(bookmarkCategory);
    }

    /**
     * 修改书签分类
     * 
     * @param bookmarkCategory 书签分类
     * @return 结果
     */
    @Override
    public int updateBookmarkCategory(BookmarkCategory bookmarkCategory)
    {
        bookmarkCategory.setUpdateTime(DateUtils.getNowDate());
        return bookmarkCategoryMapper.updateBookmarkCategory(bookmarkCategory);
    }

    /**
     * 批量删除书签分类
     * 
     * @param bookmarkCategoryIds 需要删除的书签分类主键
     * @return 结果
     */
    @Override
    public int deleteBookmarkCategoryByBookmarkCategoryIds(Long[] bookmarkCategoryIds)
    {
        return bookmarkCategoryMapper.deleteBookmarkCategoryByBookmarkCategoryIds(bookmarkCategoryIds);
    }

    /**
     * 删除书签分类信息
     * 
     * @param bookmarkCategoryId 书签分类主键
     * @return 结果
     */
    @Override
    public int deleteBookmarkCategoryByBookmarkCategoryId(Long bookmarkCategoryId)
    {
        return bookmarkCategoryMapper.deleteBookmarkCategoryByBookmarkCategoryId(bookmarkCategoryId);
    }
}
