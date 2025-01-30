package com.ruoyi.project.admin.domain;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * VIP类型对象 coco_vip_type
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_vip_type")
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
}
