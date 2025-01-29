package com.ruoyi.project.admin.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 设置变更日志对象 coco_setting_log
 * 
 * @author sinvon
 * @date 2025-01-30
 */
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

    public void setSettingLogId(Long settingLogId) 
    {
        this.settingLogId = settingLogId;
    }

    public Long getSettingLogId() 
    {
        return settingLogId;
    }
    public void setSettingKey(String settingKey) 
    {
        this.settingKey = settingKey;
    }

    public String getSettingKey() 
    {
        return settingKey;
    }
    public void setOldValue(String oldValue) 
    {
        this.oldValue = oldValue;
    }

    public String getOldValue() 
    {
        return oldValue;
    }
    public void setNewValue(String newValue) 
    {
        this.newValue = newValue;
    }

    public String getNewValue() 
    {
        return newValue;
    }
    public void setOperatorId(Long operatorId) 
    {
        this.operatorId = operatorId;
    }

    public Long getOperatorId() 
    {
        return operatorId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("settingLogId", getSettingLogId())
            .append("settingKey", getSettingKey())
            .append("oldValue", getOldValue())
            .append("newValue", getNewValue())
            .append("operatorId", getOperatorId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
