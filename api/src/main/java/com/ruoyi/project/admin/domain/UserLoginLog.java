package com.ruoyi.project.admin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 用户登录日志对象 coco_user_login_log
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public class UserLoginLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 日志ID */
    private Long userLoginLogId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 登录IP */
    @Excel(name = "登录IP")
    private String loginIp;

    /** 登录设备 */
    @Excel(name = "登录设备")
    private String device;

    /** 登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "登录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date loginTime;

    public void setUserLoginLogId(Long userLoginLogId) 
    {
        this.userLoginLogId = userLoginLogId;
    }

    public Long getUserLoginLogId() 
    {
        return userLoginLogId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setLoginIp(String loginIp) 
    {
        this.loginIp = loginIp;
    }

    public String getLoginIp() 
    {
        return loginIp;
    }
    public void setDevice(String device) 
    {
        this.device = device;
    }

    public String getDevice() 
    {
        return device;
    }
    public void setLoginTime(Date loginTime) 
    {
        this.loginTime = loginTime;
    }

    public Date getLoginTime() 
    {
        return loginTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userLoginLogId", getUserLoginLogId())
            .append("userId", getUserId())
            .append("loginIp", getLoginIp())
            .append("device", getDevice())
            .append("loginTime", getLoginTime())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
