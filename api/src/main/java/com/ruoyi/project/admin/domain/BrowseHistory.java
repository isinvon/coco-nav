package com.ruoyi.project.admin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 用户浏览历史对象 coco_browse_history
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public class BrowseHistory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 历史记录ID */
    private Long browseHistoryId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 书签ID */
    @Excel(name = "书签ID")
    private Long bookmarkId;

    /** 浏览时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "浏览时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date viewTime;

    /** IP地址 */
    @Excel(name = "IP地址")
    private String ip;

    public void setBrowseHistoryId(Long browseHistoryId) 
    {
        this.browseHistoryId = browseHistoryId;
    }

    public Long getBrowseHistoryId() 
    {
        return browseHistoryId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setBookmarkId(Long bookmarkId) 
    {
        this.bookmarkId = bookmarkId;
    }

    public Long getBookmarkId() 
    {
        return bookmarkId;
    }
    public void setViewTime(Date viewTime) 
    {
        this.viewTime = viewTime;
    }

    public Date getViewTime() 
    {
        return viewTime;
    }
    public void setIp(String ip) 
    {
        this.ip = ip;
    }

    public String getIp() 
    {
        return ip;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("browseHistoryId", getBrowseHistoryId())
            .append("userId", getUserId())
            .append("bookmarkId", getBookmarkId())
            .append("viewTime", getViewTime())
            .append("ip", getIp())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
