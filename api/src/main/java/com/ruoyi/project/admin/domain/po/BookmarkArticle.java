package com.ruoyi.project.admin.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 书签文章对象 coco_bookmark_article
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_bookmark_article")
public class BookmarkArticle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 书签ID */
    @Excel(name = "书签ID")
    private Long bookmarkId;

    /** 文章内容 */
    @Excel(name = "文章内容")
    private String content;
}
