package com.ruoyi.project.admin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 搜索日志对象 coco_search_log
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public class SearchLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 日志ID */
    private Long searchLogId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 搜索关键词 */
    @Excel(name = "搜索关键词")
    private String keyword;

    /** 搜索时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "搜索时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date searchTime;

    /** IP地址 */
    @Excel(name = "IP地址")
    private String ip;

    public void setSearchLogId(Long searchLogId) 
    {
        this.searchLogId = searchLogId;
    }

    public Long getSearchLogId() 
    {
        return searchLogId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setKeyword(String keyword) 
    {
        this.keyword = keyword;
    }

    public String getKeyword() 
    {
        return keyword;
    }
    public void setSearchTime(Date searchTime) 
    {
        this.searchTime = searchTime;
    }

    public Date getSearchTime() 
    {
        return searchTime;
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
            .append("searchLogId", getSearchLogId())
            .append("userId", getUserId())
            .append("keyword", getKeyword())
            .append("searchTime", getSearchTime())
            .append("ip", getIp())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
