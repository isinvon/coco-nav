package com.ruoyi.project.admin.service;

import java.util.List;
import com.ruoyi.project.admin.domain.BookmarkCategory;

/**
 * 书签分类Service接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public interface IBookmarkCategoryCustomService
{
    /**
     * 查询书签分类
     * 
     * @param bookmarkCategoryId 书签分类主键
     * @return 书签分类
     */
    public BookmarkCategory selectBookmarkCategoryByBookmarkCategoryId(Long bookmarkCategoryId);

    /**
     * 查询书签分类列表
     * 
     * @param bookmarkCategory 书签分类
     * @return 书签分类集合
     */
    public List<BookmarkCategory> selectBookmarkCategoryList(BookmarkCategory bookmarkCategory);

    /**
     * 新增书签分类
     * 
     * @param bookmarkCategory 书签分类
     * @return 结果
     */
    public int insertBookmarkCategory(BookmarkCategory bookmarkCategory);

    /**
     * 修改书签分类
     * 
     * @param bookmarkCategory 书签分类
     * @return 结果
     */
    public int updateBookmarkCategory(BookmarkCategory bookmarkCategory);

    /**
     * 批量删除书签分类
     * 
     * @param bookmarkCategoryIds 需要删除的书签分类主键集合
     * @return 结果
     */
    public int deleteBookmarkCategoryByBookmarkCategoryIds(Long[] bookmarkCategoryIds);

    /**
     * 删除书签分类信息
     * 
     * @param bookmarkCategoryId 书签分类主键
     * @return 结果
     */
    public int deleteBookmarkCategoryByBookmarkCategoryId(Long bookmarkCategoryId);
}
