package com.ruoyi.project.admin.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 友情链接对象 coco_friendlink
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
public class Friendlink extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 友链ID */
    private Long friendlinkId;

    /** 网站作者 */
    @Excel(name = "网站作者")
    private String author;

    /** 网站名称 */
    @Excel(name = "网站名称")
    private String name;

    /** 网站地址 */
    @Excel(name = "网站地址")
    private String url;

    /** 网站LOGO */
    @Excel(name = "网站LOGO")
    private String logo;

    /** 网站描述 */
    @Excel(name = "网站描述")
    private String description;

    /** 0-下架 1-正常 */
    @Excel(name = "0-下架 1-正常")
    private Integer status;

    /** 排序值 */
    @Excel(name = "排序值")
    private Integer sortOrder;
}
