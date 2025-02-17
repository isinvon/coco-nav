package com.ruoyi.project.admin.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 书签分类对象 coco_bookmark_category
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_bookmark_category")
public class BookmarkCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String name;

    /** 父分类ID */
    @Excel(name = "父分类ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long parentId;

    /** 排序值 */
    @Excel(name = "排序值")
    private Integer sortOrder;

    /** 分类颜色代码 */
    @Excel(name = "分类颜色代码")
    private String color;
}
