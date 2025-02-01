package com.ruoyi.project.admin.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 用户管理对象 coco_user
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_user")
public class User extends BaseEntity
{
    private static final long serialVersionUID = 1L;

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
}
