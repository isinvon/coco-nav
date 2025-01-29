package com.ruoyi.project.admin.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 系统设置对象 coco_setting
 * 
 * @author sinvon
 * @date 2025-01-30
 */
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

    public void setSettingId(Long settingId) 
    {
        this.settingId = settingId;
    }

    public Long getSettingId() 
    {
        return settingId;
    }
    public void setKey(String key) 
    {
        this.key = key;
    }

    public String getKey() 
    {
        return key;
    }
    public void setValue(String value) 
    {
        this.value = value;
    }

    public String getValue() 
    {
        return value;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("settingId", getSettingId())
            .append("key", getKey())
            .append("value", getValue())
            .append("description", getDescription())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
