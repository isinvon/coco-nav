package com.ruoyi.project.admin.domain;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 支付方式对象 coco_payment_type
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_payment_type")
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
}
