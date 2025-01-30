package com.ruoyi.project.admin.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 书签文章对象 coco_bookmark_article
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
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
}
