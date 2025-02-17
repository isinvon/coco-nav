package com.ruoyi.common.model.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 网站信息对象 coco_website_info
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_website_info")
public class WebsiteInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 网站标题 */
    @Excel(name = "网站标题")
    private String title;

    /** SEO关键词 */
    @Excel(name = "SEO关键词")
    private String keywords;

    /** 网站描述 */
    @Excel(name = "网站描述")
    private String description;

    /** 备案号 */
    @Excel(name = "备案号")
    private String icp;

    /** 系统版本 */
    @Excel(name = "系统版本")
    private String version;
}
