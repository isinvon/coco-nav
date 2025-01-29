package com.ruoyi.project.admin.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 地区信息对象 coco_area
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public class Area extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 地区ID */
    private Long areaId;

    /** 地区名称 */
    @Excel(name = "地区名称")
    private String name;

    /** 上级地区ID */
    @Excel(name = "上级地区ID")
    private Long parentId;

    /** 行政级别：1-省 2-市 3-区 */
    @Excel(name = "行政级别：1-省 2-市 3-区")
    private Integer level;

    /** 行政编码 */
    @Excel(name = "行政编码")
    private String code;

    public void setAreaId(Long areaId) 
    {
        this.areaId = areaId;
    }

    public Long getAreaId() 
    {
        return areaId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setLevel(Integer level) 
    {
        this.level = level;
    }

    public Integer getLevel() 
    {
        return level;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("areaId", getAreaId())
            .append("name", getName())
            .append("parentId", getParentId())
            .append("level", getLevel())
            .append("code", getCode())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
