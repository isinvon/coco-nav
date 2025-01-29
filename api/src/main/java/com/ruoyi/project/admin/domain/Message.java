package com.ruoyi.project.admin.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 留言管理对象 coco_message
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public class Message extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 留言ID */
    private Long messageId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 留言内容 */
    @Excel(name = "留言内容")
    private String content;

    /** 父留言ID */
    @Excel(name = "父留言ID")
    private Long parentId;

    /** 0-删除 1-正常 */
    @Excel(name = "0-删除 1-正常")
    private Integer status;

    public void setMessageId(Long messageId) 
    {
        this.messageId = messageId;
    }

    public Long getMessageId() 
    {
        return messageId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("messageId", getMessageId())
            .append("userId", getUserId())
            .append("content", getContent())
            .append("parentId", getParentId())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
