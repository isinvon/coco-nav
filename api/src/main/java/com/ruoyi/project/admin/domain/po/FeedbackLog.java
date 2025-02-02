package com.ruoyi.project.admin.domain.po;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 反馈处理日志对象 coco_feedback_log
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_feedback_log")
public class FeedbackLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 反馈ID */
    @Excel(name = "反馈ID")
    private Long feedbackId;

    /** 操作类型 */
    @Excel(name = "操作类型")
    private Integer action;

    /** 操作人ID */
    @Excel(name = "操作人ID")
    private Long operatorId;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date logTime;
}
