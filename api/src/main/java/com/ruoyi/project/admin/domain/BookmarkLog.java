package com.ruoyi.project.admin.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 书签操作日志对象 coco_bookmark_log
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public class BookmarkLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 日志ID */
    private Long bookmarkLogId;

    /** 书签ID */
    @Excel(name = "书签ID")
    private Long bookmarkId;

    /** 操作类型 */
    @Excel(name = "操作类型")
    private Integer action;

    /** 操作人ID */
    @Excel(name = "操作人ID")
    private Long operatorId;

    public void setBookmarkLogId(Long bookmarkLogId) 
    {
        this.bookmarkLogId = bookmarkLogId;
    }

    public Long getBookmarkLogId() 
    {
        return bookmarkLogId;
    }
    public void setBookmarkId(Long bookmarkId) 
    {
        this.bookmarkId = bookmarkId;
    }

    public Long getBookmarkId() 
    {
        return bookmarkId;
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
            .append("bookmarkLogId", getBookmarkLogId())
            .append("bookmarkId", getBookmarkId())
            .append("action", getAction())
            .append("operatorId", getOperatorId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
