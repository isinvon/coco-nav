package com.ruoyi.project.admin.domain.po;

import com.baomidou.mybatisplus.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

/**
 * 书签与标签关联对象 coco_bookmark_tag_relation
 *
 * @author sinvon
 * @since 2025-02-09
 */
@Data
@TableName("coco_bookmark_tag_relation")
public class BookmarkTagRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.ASSIGN_ID) // 使用雪花算法ID
    @Excel(name = "主键ID")
    private Long id;

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
