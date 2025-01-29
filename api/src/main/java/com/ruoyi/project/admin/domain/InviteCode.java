package com.ruoyi.project.admin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 邀请码管理对象 coco_invite_code
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public class InviteCode extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 邀请码ID */
    private Long inviteCodeId;

    /** 邀请码 */
    @Excel(name = "邀请码")
    private String code;

    /** 创建人ID */
    @Excel(name = "创建人ID")
    private Long creatorId;

    /** 使用者ID */
    @Excel(name = "使用者ID")
    private Long userId;

    /** 0-未使用 1-已使用 */
    @Excel(name = "0-未使用 1-已使用")
    private Integer status;

    /** 过期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "过期时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expireTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateTme;

    public void setInviteCodeId(Long inviteCodeId) 
    {
        this.inviteCodeId = inviteCodeId;
    }

    public Long getInviteCodeId() 
    {
        return inviteCodeId;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setCreatorId(Long creatorId) 
    {
        this.creatorId = creatorId;
    }

    public Long getCreatorId() 
    {
        return creatorId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setExpireTime(Date expireTime) 
    {
        this.expireTime = expireTime;
    }

    public Date getExpireTime() 
    {
        return expireTime;
    }
    public void setUpdateTme(Date updateTme) 
    {
        this.updateTme = updateTme;
    }

    public Date getUpdateTme() 
    {
        return updateTme;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("inviteCodeId", getInviteCodeId())
            .append("code", getCode())
            .append("creatorId", getCreatorId())
            .append("userId", getUserId())
            .append("status", getStatus())
            .append("expireTime", getExpireTime())
            .append("createTime", getCreateTime())
            .append("updateTme", getUpdateTme())
            .toString();
    }
}
