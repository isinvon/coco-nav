package com.ruoyi.project.admin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 用户管理对象 coco_user
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public class User extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户唯一ID */
    private Long userId;

    /** 登录用户名 */
    @Excel(name = "登录用户名")
    private String username;

    /** 加密密码 */
    @Excel(name = "加密密码")
    private String password;

    /** 绑定邮箱 */
    @Excel(name = "绑定邮箱")
    private String email;

    /** 用户等级ID */
    @Excel(name = "用户等级ID")
    private Long userGradeId;

    /** 头像URL */
    @Excel(name = "头像URL")
    private String avatar;

    /** 状态：0-禁用 1-正常 2-未激活 */
    @Excel(name = "状态：0-禁用 1-正常 2-未激活")
    private Integer status;

    /** 注册时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "注册时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date regTime;

    /** 最后登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastLogin;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setUserGradeId(Long userGradeId) 
    {
        this.userGradeId = userGradeId;
    }

    public Long getUserGradeId() 
    {
        return userGradeId;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setRegTime(Date regTime) 
    {
        this.regTime = regTime;
    }

    public Date getRegTime() 
    {
        return regTime;
    }
    public void setLastLogin(Date lastLogin) 
    {
        this.lastLogin = lastLogin;
    }

    public Date getLastLogin() 
    {
        return lastLogin;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("username", getUsername())
            .append("password", getPassword())
            .append("email", getEmail())
            .append("userGradeId", getUserGradeId())
            .append("avatar", getAvatar())
            .append("status", getStatus())
            .append("regTime", getRegTime())
            .append("lastLogin", getLastLogin())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
