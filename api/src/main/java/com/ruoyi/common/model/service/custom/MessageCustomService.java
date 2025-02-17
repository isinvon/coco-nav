package com.ruoyi.common.model.service.custom;

import java.util.List;
import com.ruoyi.common.model.domain.po.Message;

/**
 * 留言管理Service接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public interface MessageCustomService
{
    /**
     * 查询留言管理
     * 
     * @param messageId 留言管理主键
     * @return 留言管理
     */
    public Message selectMessageByMessageId(Long messageId);

    /**
     * 查询留言管理列表
     * 
     * @param message 留言管理
     * @return 留言管理集合
     */
    public List<Message> selectMessageList(Message message);

    /**
     * 新增留言管理
     * 
     * @param message 留言管理
     * @return 结果
     */
    public int insertMessage(Message message);

    /**
     * 修改留言管理
     * 
     * @param message 留言管理
     * @return 结果
     */
    public int updateMessage(Message message);

    /**
     * 批量删除留言管理
     * 
     * @param messageIds 需要删除的留言管理主键集合
     * @return 结果
     */
    public int deleteMessageByMessageIds(Long[] messageIds);

    /**
     * 删除留言管理信息
     * 
     * @param messageId 留言管理主键
     * @return 结果
     */
    public int deleteMessageByMessageId(Long messageId);
}
