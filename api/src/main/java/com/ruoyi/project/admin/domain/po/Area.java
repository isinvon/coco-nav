package com.ruoyi.project.admin.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.enums.ThemeColor;
import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.project.admin.domain.bo.TypeBo;
import lombok.Data;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;

import java.io.Serializable;
import java.util.List;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Integer id;

    /** 地区名称 */
    @Excel(name = "地区名称")
    private String name;

    /** 父级ID */
    @Excel(name = "父级ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long parentId;

    /** 行政级别：1-省 2-市 3-区 */
    @Excel(name = "行政级别：1-省 2-市 3-区")
    private Integer depth;

    /** 地区排序 */
    @Excel(name = "地区排序")
    private String sort;

    /**
     * 地区级别 - 省：1
     */
    public static final int AREA_DEPTH_PROVINCE = 1;
    /**
     * 地区级别 - 市：2
     */
    public static final int AREA_DEPTH_CITY = 2;
    /**
     * 地区级别 - 区：3
     */
    public static final int AREA_DEPTH_DISTRICT = 3;

    public static final List<TypeBo> areaDepthTypeList = List.of(
            new TypeBo(AREA_DEPTH_PROVINCE, "省", ThemeColor.CORAL_PINK.getHexCode()),
            new TypeBo(AREA_DEPTH_CITY, "市", ThemeColor.PEACH_ORANGE.getHexCode()),
            new TypeBo(AREA_DEPTH_DISTRICT, "区", ThemeColor.MINT_GREEN.getHexCode())
    );
}
