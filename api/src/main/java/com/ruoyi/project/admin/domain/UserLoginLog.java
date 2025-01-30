package com.ruoyi.project.admin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 用户登录日志对象 coco_user_login_log
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
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
}
