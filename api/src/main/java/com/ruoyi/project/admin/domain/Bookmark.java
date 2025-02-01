package com.ruoyi.project.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 书签管理对象 coco_bookmark
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_bookmark")
public class Bookmark extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 网站标题 */
    @Excel(name = "网站标题")
    private String title;

    /** 网站地址 */
    @Excel(name = "网站地址")
    private String url;

    /** 分类ID */
    @Excel(name = "分类ID")
    private Long bookmarkCategoryId;

    /** 网站图标 */
    @Excel(name = "网站图标")
    private String icon;

    /** 点击次数 */
    @Excel(name = "点击次数")
    private Integer clickCount;

    /** 排序值 */
    @Excel(name = "排序值")
    private Integer sortOrder;

    /** 0-删除 1-正常 */
    @Excel(name = "0-删除 1-正常")
    private Integer status;
}
