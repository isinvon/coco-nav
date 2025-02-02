package com.ruoyi.project.admin.domain.po;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 通知管理对象 coco_notice
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_notice")
public class Notice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 通知标题 */
    @Excel(name = "通知标题")
    private String title;

    /** 通知内容 */
    @Excel(name = "通知内容")
    private String content;

    /** 1-系统公告 2-个人消息 */
    @Excel(name = "1-系统公告 2-个人消息")
    private Integer type;

    /** 发送人ID */
    @Excel(name = "发送人ID")
    private Long senderId;

    /** 发送时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发送时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sendTime;

    /** 过期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "过期时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expireTime;
}
