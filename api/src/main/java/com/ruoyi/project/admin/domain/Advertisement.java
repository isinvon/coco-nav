package com.ruoyi.project.admin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 广告管理对象 coco_advertisement
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public class Advertisement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 广告ID */
    private Long advertisementId;

    /** 广告标题 */
    @Excel(name = "广告标题")
    private String title;

    /** 广告内容 */
    @Excel(name = "广告内容")
    private String content;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 0-下架 1-正常 */
    @Excel(name = "0-下架 1-正常")
    private Integer status;

    /** 点击次数 */
    @Excel(name = "点击次数")
    private Integer clickCount;

    /** 排序值 */
    @Excel(name = "排序值")
    private Integer sortOrder;

    /** 广告的宽度 */
    @Excel(name = "广告的宽度")
    private Integer width;

    /** 广告的高度 */
    @Excel(name = "广告的高度")
    private Integer height;

    public void setAdvertisementId(Long advertisementId) 
    {
        this.advertisementId = advertisementId;
    }

    public Long getAdvertisementId() 
    {
        return advertisementId;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setClickCount(Integer clickCount) 
    {
        this.clickCount = clickCount;
    }

    public Integer getClickCount() 
    {
        return clickCount;
    }
    public void setSortOrder(Integer sortOrder) 
    {
        this.sortOrder = sortOrder;
    }

    public Integer getSortOrder() 
    {
        return sortOrder;
    }
    public void setWidth(Integer width) 
    {
        this.width = width;
    }

    public Integer getWidth() 
    {
        return width;
    }
    public void setHeight(Integer height) 
    {
        this.height = height;
    }

    public Integer getHeight() 
    {
        return height;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("advertisementId", getAdvertisementId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("status", getStatus())
            .append("clickCount", getClickCount())
            .append("sortOrder", getSortOrder())
            .append("width", getWidth())
            .append("height", getHeight())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
