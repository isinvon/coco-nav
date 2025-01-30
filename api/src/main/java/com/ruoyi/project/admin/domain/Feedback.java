package com.ruoyi.project.admin.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 用户反馈对象 coco_feedback
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
public class Feedback extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 反馈ID */
    private Long feedbackId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 反馈类型ID */
    @Excel(name = "反馈类型ID")
    private Long feedbackTypeId;

    /** 反馈内容 */
    @Excel(name = "反馈内容")
    private String content;

    /** 0-待处理 1-已处理 */
    @Excel(name = "0-待处理 1-已处理")
    private Integer status;
}
