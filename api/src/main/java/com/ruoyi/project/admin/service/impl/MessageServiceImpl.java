package com.ruoyi.project.admin.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.admin.service.MessageService;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.project.admin.mapper.MessageMapper;
import com.ruoyi.project.admin.domain.po.Message;
import com.ruoyi.project.admin.service.custom.MessageCustomService;
import org.springframework.stereotype.Service;

/**
 * 留言管理Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper,Message> implements MessageCustomService, MessageService
{
    @Autowired
    private MessageMapper messageMapper;

    /**
     * 查询留言管理
     * 
     * @param messageId 留言管理主键
     * @return 留言管理
     */
    @Override
    public Message selectMessageByMessageId(Long messageId)
    {
        return messageMapper.selectMessageByMessageId(messageId);
    }

    /**
     * 查询留言管理列表
     * 
     * @param message 留言管理
     * @return 留言管理
     */
    @Override
    public List<Message> selectMessageList(Message message)
    {
        return messageMapper.selectMessageList(message);
    }

    /**
     * 新增留言管理
     * 
     * @param message 留言管理
     * @return 结果
     */
    @Override
    public int insertMessage(Message message)
    {
        message.setCreateTime(DateUtils.getNowDate());
        return messageMapper.insertMessage(message);
    }

    /**
     * 修改留言管理
     * 
     * @param message 留言管理
     * @return 结果
     */
    @Override
    public int updateMessage(Message message)
    {
        message.setUpdateTime(DateUtils.getNowDate());
        return messageMapper.updateMessage(message);
    }

    /**
     * 批量删除留言管理
     * 
     * @param messageIds 需要删除的留言管理主键
     * @return 结果
     */
    @Override
    public int deleteMessageByMessageIds(Long[] messageIds)
    {
        return messageMapper.deleteMessageByMessageIds(messageIds);
    }

    /**
     * 删除留言管理信息
     * 
     * @param messageId 留言管理主键
     * @return 结果
     */
    @Override
    public int deleteMessageByMessageId(Long messageId)
    {
        return messageMapper.deleteMessageByMessageId(messageId);
    }
}
