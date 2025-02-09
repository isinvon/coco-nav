package com.ruoyi.project.admin.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 书签与标签关联对象 coco_bookmark_tag_relation
 *
 * @author sinvon
 * @since 2025-02-09
 */
@Data
@TableName("coco_bookmark_tag_relation")
public class BookmarkTagRelation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 书签ID
     */
    @Excel(name = "书签ID")
    private Long bookmarkId;

    /**
     * 标签ID
     */
    @Excel(name = "标签ID")
    private Long bookmarkTagId;
}
