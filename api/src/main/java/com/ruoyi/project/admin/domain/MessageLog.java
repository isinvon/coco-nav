package com.ruoyi.project.admin.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 留言操作日志对象 coco_message_log
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public class MessageLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 日志ID */
    private Long messageLogId;

    /** 留言ID */
    @Excel(name = "留言ID")
    private Long messageId;

    /** 操作类型 */
    @Excel(name = "操作类型")
    private Integer action;

    /** 操作人ID */
    @Excel(name = "操作人ID")
    private Long operatorId;

    public void setMessageLogId(Long messageLogId) 
    {
        this.messageLogId = messageLogId;
    }

    public Long getMessageLogId() 
    {
        return messageLogId;
    }
    public void setMessageId(Long messageId) 
    {
        this.messageId = messageId;
    }

    public Long getMessageId() 
    {
        return messageId;
    }
    public void setAction(Integer action) 
    {
        this.action = action;
    }

    public Integer getAction() 
    {
        return action;
    }
    public void setOperatorId(Long operatorId) 
    {
        this.operatorId = operatorId;
    }

    public Long getOperatorId() 
    {
        return operatorId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("messageLogId", getMessageLogId())
            .append("messageId", getMessageId())
            .append("action", getAction())
            .append("operatorId", getOperatorId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
