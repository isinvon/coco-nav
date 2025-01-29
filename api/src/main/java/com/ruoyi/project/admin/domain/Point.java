package com.ruoyi.project.admin.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 积分账户对象 coco_point
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public class Point extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 积分账户ID */
    private Long pointId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 当前积分 */
    @Excel(name = "当前积分")
    private Integer balance;

    public void setPointId(Long pointId) 
    {
        this.pointId = pointId;
    }

    public Long getPointId() 
    {
        return pointId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setBalance(Integer balance) 
    {
        this.balance = balance;
    }

    public Integer getBalance() 
    {
        return balance;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("pointId", getPointId())
            .append("userId", getUserId())
            .append("balance", getBalance())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
