package com.ruoyi.project.admin.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 广告操作日志对象 coco_advertisement_log
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public class AdvertisementLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 日志ID */
    private Long advertisementLogId;

    /** 广告ID */
    @Excel(name = "广告ID")
    private Long advertisementId;

    /** 操作类型 */
    @Excel(name = "操作类型")
    private Integer action;

    /** 操作人ID */
    @Excel(name = "操作人ID")
    private Long operatorId;

    public void setAdvertisementLogId(Long advertisementLogId) 
    {
        this.advertisementLogId = advertisementLogId;
    }

    public Long getAdvertisementLogId() 
    {
        return advertisementLogId;
    }
    public void setAdvertisementId(Long advertisementId) 
    {
        this.advertisementId = advertisementId;
    }

    public Long getAdvertisementId() 
    {
        return advertisementId;
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
            .append("advertisementLogId", getAdvertisementLogId())
            .append("advertisementId", getAdvertisementId())
            .append("action", getAction())
            .append("operatorId", getOperatorId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
