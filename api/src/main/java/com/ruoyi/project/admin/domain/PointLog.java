package com.ruoyi.project.admin.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 积分流水对象 coco_point_log
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public class PointLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 日志ID */
    private Long pointLogId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 1-增加 2-扣除 3-冻结 */
    @Excel(name = "1-增加 2-扣除 3-冻结")
    private Integer changeType;

    /** 变动数值 */
    @Excel(name = "变动数值")
    private Integer amount;

    /** 变动后积分余额 */
    @Excel(name = "变动后积分余额")
    private Integer balance;

    /** 来源模块 */
    @Excel(name = "来源模块")
    private String source;

    /** 操作类型 */
    @Excel(name = "操作类型")
    private Integer action;

    /** 变动说明 */
    @Excel(name = "变动说明")
    private String description;

    public void setPointLogId(Long pointLogId) 
    {
        this.pointLogId = pointLogId;
    }

    public Long getPointLogId() 
    {
        return pointLogId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setChangeType(Integer changeType) 
    {
        this.changeType = changeType;
    }

    public Integer getChangeType() 
    {
        return changeType;
    }
    public void setAmount(Integer amount) 
    {
        this.amount = amount;
    }

    public Integer getAmount() 
    {
        return amount;
    }
    public void setBalance(Integer balance) 
    {
        this.balance = balance;
    }

    public Integer getBalance() 
    {
        return balance;
    }
    public void setSource(String source) 
    {
        this.source = source;
    }

    public String getSource() 
    {
        return source;
    }
    public void setAction(Integer action) 
    {
        this.action = action;
    }

    public Integer getAction() 
    {
        return action;
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
            .append("pointLogId", getPointLogId())
            .append("userId", getUserId())
            .append("changeType", getChangeType())
            .append("amount", getAmount())
            .append("balance", getBalance())
            .append("source", getSource())
            .append("action", getAction())
            .append("description", getDescription())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
