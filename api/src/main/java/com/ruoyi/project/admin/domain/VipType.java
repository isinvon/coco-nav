package com.ruoyi.project.admin.domain;

import java.math.BigDecimal;

import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * VIP类型对象 coco_vip_type
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public class VipType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 类型ID */
    private Long vipTypeId;

    /** VIP类型名称 */
    @Excel(name = "VIP类型名称")
    private String typeName;

    /** 有效期（天） */
    @Excel(name = "有效期", readConverterExp = "天=")
    private Long duration;

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal price;

    /** 特权说明 */
    @Excel(name = "特权说明")
    private String privileges;

    public void setVipTypeId(Long vipTypeId) 
    {
        this.vipTypeId = vipTypeId;
    }

    public Long getVipTypeId() 
    {
        return vipTypeId;
    }
    public void setTypeName(String typeName) 
    {
        this.typeName = typeName;
    }

    public String getTypeName() 
    {
        return typeName;
    }
    public void setDuration(Long duration) 
    {
        this.duration = duration;
    }

    public Long getDuration() 
    {
        return duration;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setPrivileges(String privileges) 
    {
        this.privileges = privileges;
    }

    public String getPrivileges() 
    {
        return privileges;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("vipTypeId", getVipTypeId())
            .append("typeName", getTypeName())
            .append("duration", getDuration())
            .append("price", getPrice())
            .append("privileges", getPrivileges())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
