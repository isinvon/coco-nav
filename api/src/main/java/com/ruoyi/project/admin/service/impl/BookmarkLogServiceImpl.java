package com.ruoyi.project.admin.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.admin.service.BookmarkLogService;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.admin.service.custom.BookmarkLogCustomService;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.project.admin.mapper.BookmarkLogMapper;
import com.ruoyi.project.admin.domain.BookmarkLog;
import org.springframework.stereotype.Service;

/**
 * 书签操作日志Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class BookmarkLogServiceImpl extends ServiceImpl<BookmarkLogMapper,BookmarkLog> implements BookmarkLogCustomService, BookmarkLogService
{
    @Autowired
    private BookmarkLogMapper bookmarkLogMapper;

    /**
     * 查询书签操作日志
     * 
     * @param bookmarkLogId 书签操作日志主键
     * @return 书签操作日志
     */
    @Override
    public BookmarkLog selectBookmarkLogByBookmarkLogId(Long bookmarkLogId)
    {
        return bookmarkLogMapper.selectBookmarkLogByBookmarkLogId(bookmarkLogId);
    }

    /**
     * 查询书签操作日志列表
     * 
     * @param bookmarkLog 书签操作日志
     * @return 书签操作日志
     */
    @Override
    public List<BookmarkLog> selectBookmarkLogList(BookmarkLog bookmarkLog)
    {
        return bookmarkLogMapper.selectBookmarkLogList(bookmarkLog);
    }

    /**
     * 新增书签操作日志
     * 
     * @param bookmarkLog 书签操作日志
     * @return 结果
     */
    @Override
    public int insertBookmarkLog(BookmarkLog bookmarkLog)
    {
        bookmarkLog.setCreateTime(DateUtils.getNowDate());
        return bookmarkLogMapper.insertBookmarkLog(bookmarkLog);
    }

    /**
     * 修改书签操作日志
     * 
     * @param bookmarkLog 书签操作日志
     * @return 结果
     */
    @Override
    public int updateBookmarkLog(BookmarkLog bookmarkLog)
    {
        bookmarkLog.setUpdateTime(DateUtils.getNowDate());
        return bookmarkLogMapper.updateBookmarkLog(bookmarkLog);
    }

    /**
     * 批量删除书签操作日志
     * 
     * @param bookmarkLogIds 需要删除的书签操作日志主键
     * @return 结果
     */
    @Override
    public int deleteBookmarkLogByBookmarkLogIds(Long[] bookmarkLogIds)
    {
        return bookmarkLogMapper.deleteBookmarkLogByBookmarkLogIds(bookmarkLogIds);
    }

    /**
     * 删除书签操作日志信息
     * 
     * @param bookmarkLogId 书签操作日志主键
     * @return 结果
     */
    @Override
    public int deleteBookmarkLogByBookmarkLogId(Long bookmarkLogId)
    {
        return bookmarkLogMapper.deleteBookmarkLogByBookmarkLogId(bookmarkLogId);
    }
}
