package com.ruoyi.project.admin.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;

import java.io.Serializable;

/**
 * 地区信息对象 coco_area
 * 在 coco_area 表中地区编号就是 “id” 字段
 *
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_area")
public class Area implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 地区ID */
    @Excel(name = "地区ID")
    private Integer id;

    /** 地区名称 */
    @Excel(name = "地区名称")
    private String name;

    /** 父级ID */
    @Excel(name = "父级ID")
    private Long parentId;

    /** 行政级别：1-省 2-市 3-区 */
    @Excel(name = "行政级别：1-省 2-市 3-区")
    private Integer depth;

    /** 地区排序 */
    @Excel(name = "地区排序")
    private String sort;
}
