package com.ruoyi.common.model.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.model.service.MessageLogService;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.common.model.mapper.MessageLogMapper;
import com.ruoyi.common.model.domain.po.MessageLog;
import com.ruoyi.common.model.service.custom.MessageLogCustomService;
import org.springframework.stereotype.Service;

/**
 * 留言操作日志Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class MessageLogServiceImpl extends ServiceImpl<MessageLogMapper,MessageLog> implements MessageLogCustomService, MessageLogService
{
    @Autowired
    private MessageLogMapper messageLogMapper;

    /**
     * 查询留言操作日志
     * 
     * @param messageLogId 留言操作日志主键
     * @return 留言操作日志
     */
    @Override
    public MessageLog selectMessageLogByMessageLogId(Long messageLogId)
    {
        return messageLogMapper.selectMessageLogByMessageLogId(messageLogId);
    }

    /**
     * 查询留言操作日志列表
     * 
     * @param messageLog 留言操作日志
     * @return 留言操作日志
     */
    @Override
    public List<MessageLog> selectMessageLogList(MessageLog messageLog)
    {
        return messageLogMapper.selectMessageLogList(messageLog);
    }

    /**
     * 新增留言操作日志
     * 
     * @param messageLog 留言操作日志
     * @return 结果
     */
    @Override
    public int insertMessageLog(MessageLog messageLog)
    {
        messageLog.setCreateTime(DateUtils.getNowDate());
        return messageLogMapper.insertMessageLog(messageLog);
    }

    /**
     * 修改留言操作日志
     * 
     * @param messageLog 留言操作日志
     * @return 结果
     */
    @Override
    public int updateMessageLog(MessageLog messageLog)
    {
        messageLog.setUpdateTime(DateUtils.getNowDate());
        return messageLogMapper.updateMessageLog(messageLog);
    }

    /**
     * 批量删除留言操作日志
     * 
     * @param messageLogIds 需要删除的留言操作日志主键
     * @return 结果
     */
    @Override
    public int deleteMessageLogByMessageLogIds(Long[] messageLogIds)
    {
        return messageLogMapper.deleteMessageLogByMessageLogIds(messageLogIds);
    }

    /**
     * 删除留言操作日志信息
     * 
     * @param messageLogId 留言操作日志主键
     * @return 结果
     */
    @Override
    public int deleteMessageLogByMessageLogId(Long messageLogId)
    {
        return messageLogMapper.deleteMessageLogByMessageLogId(messageLogId);
    }
}
