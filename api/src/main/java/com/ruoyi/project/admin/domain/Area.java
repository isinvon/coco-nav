package com.ruoyi.project.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 地区信息对象 coco_area
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_area")
public class Area extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 地区名称 */
    @Excel(name = "地区名称")
    private String name;

    /** 上级地区ID */
    @Excel(name = "上级地区ID")
    private Long parentId;

    /** 行政级别：1-省 2-市 3-区 */
    @Excel(name = "行政级别：1-省 2-市 3-区")
    private Integer level;

    /** 行政编码 */
    @Excel(name = "行政编码")
    private String code;
}
