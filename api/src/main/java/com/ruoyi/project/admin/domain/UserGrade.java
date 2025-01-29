package com.ruoyi.project.admin.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 用户等级对象 coco_user_grade
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public class UserGrade extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 等级ID */
    private Long userGradeId;

    /** 等级名称 */
    @Excel(name = "等级名称")
    private String gradeName;

    /** 最低积分要求 */
    @Excel(name = "最低积分要求")
    private Integer minPoints;

    /** 等级特权描述 */
    @Excel(name = "等级特权描述")
    private String privileges;

    /** 等级图标 */
    @Excel(name = "等级图标")
    private String icon;

    public void setUserGradeId(Long userGradeId) 
    {
        this.userGradeId = userGradeId;
    }

    public Long getUserGradeId() 
    {
        return userGradeId;
    }
    public void setGradeName(String gradeName) 
    {
        this.gradeName = gradeName;
    }

    public String getGradeName() 
    {
        return gradeName;
    }
    public void setMinPoints(Integer minPoints) 
    {
        this.minPoints = minPoints;
    }

    public Integer getMinPoints() 
    {
        return minPoints;
    }
    public void setPrivileges(String privileges) 
    {
        this.privileges = privileges;
    }

    public String getPrivileges() 
    {
        return privileges;
    }
    public void setIcon(String icon) 
    {
        this.icon = icon;
    }

    public String getIcon() 
    {
        return icon;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userGradeId", getUserGradeId())
            .append("gradeName", getGradeName())
            .append("minPoints", getMinPoints())
            .append("privileges", getPrivileges())
            .append("icon", getIcon())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
