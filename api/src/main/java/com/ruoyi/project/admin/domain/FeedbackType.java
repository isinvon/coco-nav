package com.ruoyi.project.admin.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 反馈类型对象 coco_feedback_type
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
public class FeedbackType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 类型ID */
    private Long feedbackTypeId;

    /** 类型名称 */
    @Excel(name = "类型名称")
    private String typeName;

    /** 排序值 */
    @Excel(name = "排序值")
    private Integer sortOrder;
}
