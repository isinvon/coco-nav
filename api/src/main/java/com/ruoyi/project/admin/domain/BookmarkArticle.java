package com.ruoyi.project.admin.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 书签文章对象 coco_bookmark_article
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public class BookmarkArticle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文章ID */
    private Long articleId;

    /** 书签ID */
    @Excel(name = "书签ID")
    private Long bookmarkId;

    /** 文章内容 */
    @Excel(name = "文章内容")
    private String content;

    public void setArticleId(Long articleId) 
    {
        this.articleId = articleId;
    }

    public Long getArticleId() 
    {
        return articleId;
    }
    public void setBookmarkId(Long bookmarkId) 
    {
        this.bookmarkId = bookmarkId;
    }

    public Long getBookmarkId() 
    {
        return bookmarkId;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("articleId", getArticleId())
            .append("bookmarkId", getBookmarkId())
            .append("content", getContent())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
