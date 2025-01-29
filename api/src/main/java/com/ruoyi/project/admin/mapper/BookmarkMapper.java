package com.ruoyi.project.admin.mapper;

import java.util.List;
import com.ruoyi.project.admin.domain.Bookmark;

/**
 * 书签管理Mapper接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public interface BookmarkMapper 
{
    /**
     * 查询书签管理
     * 
     * @param bookmarkId 书签管理主键
     * @return 书签管理
     */
    public Bookmark selectBookmarkByBookmarkId(Long bookmarkId);

    /**
     * 查询书签管理列表
     * 
     * @param bookmark 书签管理
     * @return 书签管理集合
     */
    public List<Bookmark> selectBookmarkList(Bookmark bookmark);

    /**
     * 新增书签管理
     * 
     * @param bookmark 书签管理
     * @return 结果
     */
    public int insertBookmark(Bookmark bookmark);

    /**
     * 修改书签管理
     * 
     * @param bookmark 书签管理
     * @return 结果
     */
    public int updateBookmark(Bookmark bookmark);

    /**
     * 删除书签管理
     * 
     * @param bookmarkId 书签管理主键
     * @return 结果
     */
    public int deleteBookmarkByBookmarkId(Long bookmarkId);

    /**
     * 批量删除书签管理
     * 
     * @param bookmarkIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBookmarkByBookmarkIds(Long[] bookmarkIds);
}
