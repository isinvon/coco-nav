package com.ruoyi.project.admin.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 设置变更日志对象 coco_setting_log
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
public class SettingLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 日志ID */
    private Long settingLogId;

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
    private Long operatorId;
}
