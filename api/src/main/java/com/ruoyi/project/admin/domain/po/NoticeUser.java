package com.ruoyi.project.admin.domain.po;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 通知用户关系对象 coco_notice_user
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_notice_user")
public class NoticeUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 通知ID */
    @Excel(name = "通知ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long noticeId;

    /** 用户ID */
    @Excel(name = "用户ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;

    /** 0-未读 1-已读 */
    @Excel(name = "0-未读 1-已读")
    private Integer readStatus;

    /** 阅读时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "阅读时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date readTime;
}
