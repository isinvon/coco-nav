package com.ruoyi.project.admin.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 书签标签对象 coco_bookmark_tag
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public class BookmarkTag extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 标签ID */
    private Long bookmarkTagId;

    /** 标签名称 */
    @Excel(name = "标签名称")
    private String tagName;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    public void setBookmarkTagId(Long bookmarkTagId) 
    {
        this.bookmarkTagId = bookmarkTagId;
    }

    public Long getBookmarkTagId() 
    {
        return bookmarkTagId;
    }
    public void setTagName(String tagName) 
    {
        this.tagName = tagName;
    }

    public String getTagName() 
    {
        return tagName;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("bookmarkTagId", getBookmarkTagId())
            .append("tagName", getTagName())
            .append("userId", getUserId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
