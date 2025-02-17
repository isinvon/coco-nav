package com.ruoyi.common.model.service.custom;

import java.util.List;
import com.ruoyi.common.model.domain.po.MessageLog;

/**
 * 留言操作日志Service接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public interface MessageLogCustomService
{
    /**
     * 查询留言操作日志
     * 
     * @param messageLogId 留言操作日志主键
     * @return 留言操作日志
     */
    public MessageLog selectMessageLogByMessageLogId(Long messageLogId);

    /**
     * 查询留言操作日志列表
     * 
     * @param messageLog 留言操作日志
     * @return 留言操作日志集合
     */
    public List<MessageLog> selectMessageLogList(MessageLog messageLog);

    /**
     * 新增留言操作日志
     * 
     * @param messageLog 留言操作日志
     * @return 结果
     */
    public int insertMessageLog(MessageLog messageLog);

    /**
     * 修改留言操作日志
     * 
     * @param messageLog 留言操作日志
     * @return 结果
     */
    public int updateMessageLog(MessageLog messageLog);

    /**
     * 批量删除留言操作日志
     * 
     * @param messageLogIds 需要删除的留言操作日志主键集合
     * @return 结果
     */
    public int deleteMessageLogByMessageLogIds(Long[] messageLogIds);

    /**
     * 删除留言操作日志信息
     * 
     * @param messageLogId 留言操作日志主键
     * @return 结果
     */
    public int deleteMessageLogByMessageLogId(Long messageLogId);
}
