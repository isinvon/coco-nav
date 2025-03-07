package com.ruoyi.project.admin.domain.po;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 邀请码管理对象 coco_invite_code
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_invite_code")
public class InviteCode extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 邀请码 */
    @Excel(name = "邀请码")
    private String code;

    /** 创建人ID */
    @Excel(name = "创建人ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long creatorId;

    /** 使用者ID */
    @Excel(name = "使用者ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
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
}
