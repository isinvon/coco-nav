package com.ruoyi.project.admin.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 书签分类对象 coco_bookmark_category
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public class BookmarkCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分类ID */
    private Long bookmarkCategoryId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String name;

    /** 父分类ID */
    @Excel(name = "父分类ID")
    private Long parentId;

    /** 排序值 */
    @Excel(name = "排序值")
    private Integer sortOrder;

    /** 分类颜色代码 */
    @Excel(name = "分类颜色代码")
    private String color;

    public void setBookmarkCategoryId(Long bookmarkCategoryId) 
    {
        this.bookmarkCategoryId = bookmarkCategoryId;
    }

    public Long getBookmarkCategoryId() 
    {
        return bookmarkCategoryId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setSortOrder(Integer sortOrder) 
    {
        this.sortOrder = sortOrder;
    }

    public Integer getSortOrder() 
    {
        return sortOrder;
    }
    public void setColor(String color) 
    {
        this.color = color;
    }

    public String getColor() 
    {
        return color;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("bookmarkCategoryId", getBookmarkCategoryId())
            .append("userId", getUserId())
            .append("name", getName())
            .append("parentId", getParentId())
            .append("sortOrder", getSortOrder())
            .append("color", getColor())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
