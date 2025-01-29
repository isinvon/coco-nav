package com.ruoyi.project.admin.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 反馈类型对象 coco_feedback_type
 * 
 * @author sinvon
 * @date 2025-01-30
 */
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

    public void setFeedbackTypeId(Long feedbackTypeId) 
    {
        this.feedbackTypeId = feedbackTypeId;
    }

    public Long getFeedbackTypeId() 
    {
        return feedbackTypeId;
    }
    public void setTypeName(String typeName) 
    {
        this.typeName = typeName;
    }

    public String getTypeName() 
    {
        return typeName;
    }
    public void setSortOrder(Integer sortOrder) 
    {
        this.sortOrder = sortOrder;
    }

    public Integer getSortOrder() 
    {
        return sortOrder;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("feedbackTypeId", getFeedbackTypeId())
            .append("typeName", getTypeName())
            .append("sortOrder", getSortOrder())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
