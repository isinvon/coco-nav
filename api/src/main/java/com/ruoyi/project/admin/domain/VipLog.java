package com.ruoyi.project.admin.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * VIP操作日志对象 coco_vip_log
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public class VipLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 日志ID */
    private Long vipLogId;

    /** VIP记录ID */
    @Excel(name = "VIP记录ID")
    private Long vipId;

    /** 操作类型 */
    @Excel(name = "操作类型")
    private Integer action;

    /** VIP时长 (秒) */
    @Excel(name = "VIP时长 (秒)")
    private Long duration;

    /** 操作人ID */
    @Excel(name = "操作人ID")
    private Long operatorId;

    public void setVipLogId(Long vipLogId) 
    {
        this.vipLogId = vipLogId;
    }

    public Long getVipLogId() 
    {
        return vipLogId;
    }
    public void setVipId(Long vipId) 
    {
        this.vipId = vipId;
    }

    public Long getVipId() 
    {
        return vipId;
    }
    public void setAction(Integer action) 
    {
        this.action = action;
    }

    public Integer getAction() 
    {
        return action;
    }
    public void setDuration(Long duration) 
    {
        this.duration = duration;
    }

    public Long getDuration() 
    {
        return duration;
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
            .append("vipLogId", getVipLogId())
            .append("vipId", getVipId())
            .append("action", getAction())
            .append("duration", getDuration())
            .append("operatorId", getOperatorId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
