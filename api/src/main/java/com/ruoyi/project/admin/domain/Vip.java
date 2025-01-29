package com.ruoyi.project.admin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * VIP会员对象 coco_vip
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public class Vip extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** VIP记录ID */
    private Long vipId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** VIP类型ID */
    @Excel(name = "VIP类型ID")
    private Long vipTypeId;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 0-失效 1-有效 */
    @Excel(name = "0-失效 1-有效")
    private Integer status;

    public void setVipId(Long vipId) 
    {
        this.vipId = vipId;
    }

    public Long getVipId() 
    {
        return vipId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setVipTypeId(Long vipTypeId) 
    {
        this.vipTypeId = vipTypeId;
    }

    public Long getVipTypeId() 
    {
        return vipTypeId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("vipId", getVipId())
            .append("userId", getUserId())
            .append("vipTypeId", getVipTypeId())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
