package com.ruoyi.project.admin.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 书签标签对象 coco_bookmark_tag
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_bookmark_tag")
public class BookmarkTag extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 标签名称 */
    @Excel(name = "标签名称")
    private String tagName;
}
