package com.ruoyi.project.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 系统设置对象 coco_setting
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_setting")
public class Setting extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设置ID */
    private Long settingId;

    /** 设置项键名 */
    @Excel(name = "设置项键名")
    private String key;

    /** 设置项值 */
    @Excel(name = "设置项值")
    private String value;

    /** 设置项说明 */
    @Excel(name = "设置项说明")
    private String description;
}
