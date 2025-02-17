package com.ruoyi.common.model.service.custom;

import java.util.List;
import com.ruoyi.common.model.domain.po.BookmarkTag;

/**
 * 书签标签Service接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public interface BookmarkTagCustomService
{
    /**
     * 查询书签标签
     * 
     * @param bookmarkTagId 书签标签主键
     * @return 书签标签
     */
    public BookmarkTag selectBookmarkTagByBookmarkTagId(Long bookmarkTagId);

    /**
     * 查询书签标签列表
     * 
     * @param bookmarkTag 书签标签
     * @return 书签标签集合
     */
    public List<BookmarkTag> selectBookmarkTagList(BookmarkTag bookmarkTag);

    /**
     * 新增书签标签
     * 
     * @param bookmarkTag 书签标签
     * @return 结果
     */
    public int insertBookmarkTag(BookmarkTag bookmarkTag);

    /**
     * 修改书签标签
     * 
     * @param bookmarkTag 书签标签
     * @return 结果
     */
    public int updateBookmarkTag(BookmarkTag bookmarkTag);

    /**
     * 批量删除书签标签
     * 
     * @param bookmarkTagIds 需要删除的书签标签主键集合
     * @return 结果
     */
    public int deleteBookmarkTagByBookmarkTagIds(Long[] bookmarkTagIds);

    /**
     * 删除书签标签信息
     * 
     * @param bookmarkTagId 书签标签主键
     * @return 结果
     */
    public int deleteBookmarkTagByBookmarkTagId(Long bookmarkTagId);
}
