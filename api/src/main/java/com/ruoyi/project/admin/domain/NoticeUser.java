package com.ruoyi.project.admin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 通知用户关系对象 coco_notice_user
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public class NoticeUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 关联ID */
    private Long relationId;

    /** 通知ID */
    @Excel(name = "通知ID")
    private Long noticeId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 0-未读 1-已读 */
    @Excel(name = "0-未读 1-已读")
    private Integer readStatus;

    /** 阅读时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "阅读时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date readTime;

    public void setRelationId(Long relationId) 
    {
        this.relationId = relationId;
    }

    public Long getRelationId() 
    {
        return relationId;
    }
    public void setNoticeId(Long noticeId) 
    {
        this.noticeId = noticeId;
    }

    public Long getNoticeId() 
    {
        return noticeId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setReadStatus(Integer readStatus) 
    {
        this.readStatus = readStatus;
    }

    public Integer getReadStatus() 
    {
        return readStatus;
    }
    public void setReadTime(Date readTime) 
    {
        this.readTime = readTime;
    }

    public Date getReadTime() 
    {
        return readTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("relationId", getRelationId())
            .append("noticeId", getNoticeId())
            .append("userId", getUserId())
            .append("readStatus", getReadStatus())
            .append("readTime", getReadTime())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
