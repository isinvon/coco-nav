package com.ruoyi.project.admin.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 用户反馈对象 coco_feedback
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_feedback")
public class Feedback extends BaseEntity
{
    private static final long serialVersionUID = 1L;

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
