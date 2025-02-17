package com.ruoyi.project.admin.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 反馈类型对象 coco_feedback_type
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_feedback_type")
public class FeedbackType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 类型名称 */
    @Excel(name = "类型名称")
    private String typeName;

    /** 排序值 */
    @Excel(name = "排序值")
    private Integer sortOrder;
}
