package com.ruoyi.project.admin.service.custom;

import java.util.List;
import com.ruoyi.project.admin.domain.BookmarkLog;

/**
 * 书签操作日志Service接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public interface BookmarkLogCustomService
{
    /**
     * 查询书签操作日志
     * 
     * @param bookmarkLogId 书签操作日志主键
     * @return 书签操作日志
     */
    public BookmarkLog selectBookmarkLogByBookmarkLogId(Long bookmarkLogId);

    /**
     * 查询书签操作日志列表
     * 
     * @param bookmarkLog 书签操作日志
     * @return 书签操作日志集合
     */
    public List<BookmarkLog> selectBookmarkLogList(BookmarkLog bookmarkLog);

    /**
     * 新增书签操作日志
     * 
     * @param bookmarkLog 书签操作日志
     * @return 结果
     */
    public int insertBookmarkLog(BookmarkLog bookmarkLog);

    /**
     * 修改书签操作日志
     * 
     * @param bookmarkLog 书签操作日志
     * @return 结果
     */
    public int updateBookmarkLog(BookmarkLog bookmarkLog);

    /**
     * 批量删除书签操作日志
     * 
     * @param bookmarkLogIds 需要删除的书签操作日志主键集合
     * @return 结果
     */
    public int deleteBookmarkLogByBookmarkLogIds(Long[] bookmarkLogIds);

    /**
     * 删除书签操作日志信息
     * 
     * @param bookmarkLogId 书签操作日志主键
     * @return 结果
     */
    public int deleteBookmarkLogByBookmarkLogId(Long bookmarkLogId);
}
