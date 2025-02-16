package com.ruoyi.project.admin.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 设置变更日志对象 coco_setting_log
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_setting_log")
public class SettingLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设置项键名 */
    @Excel(name = "设置项键名")
    private String settingKey;

    /** 旧值 */
    @Excel(name = "旧值")
    private String oldValue;

    /** 新值 */
    @Excel(name = "新值")
    private String newValue;

    /** 操作人ID */
    @Excel(name = "操作人ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long operatorId;
}
