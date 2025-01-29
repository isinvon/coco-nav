package com.ruoyi.project.admin.domain;

import java.math.BigDecimal;

import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 支付方式对象 coco_payment_type
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public class PaymentType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 支付方式ID */
    private Long paymentTypeId;

    /** 支付名称 */
    @Excel(name = "支付名称")
    private String name;

    /** 支付代码 */
    @Excel(name = "支付代码")
    private String code;

    /** 手续费率 */
    @Excel(name = "手续费率")
    private BigDecimal rate;

    /** 配置参数 */
    @Excel(name = "配置参数")
    private String config;

    /** 0-停用 1-启用 */
    @Excel(name = "0-停用 1-启用")
    private Integer status;

    public void setPaymentTypeId(Long paymentTypeId) 
    {
        this.paymentTypeId = paymentTypeId;
    }

    public Long getPaymentTypeId() 
    {
        return paymentTypeId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setRate(BigDecimal rate) 
    {
        this.rate = rate;
    }

    public BigDecimal getRate() 
    {
        return rate;
    }
    public void setConfig(String config) 
    {
        this.config = config;
    }

    public String getConfig() 
    {
        return config;
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
            .append("paymentTypeId", getPaymentTypeId())
            .append("name", getName())
            .append("code", getCode())
            .append("rate", getRate())
            .append("config", getConfig())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
