package com.ruoyi.common.model.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 留言管理对象 coco_message
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_message")
public class Message extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @Excel(name = "用户ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;

    /** 留言内容 */
    @Excel(name = "留言内容")
    private String content;

    /** 父留言ID */
    @Excel(name = "父留言ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long parentId;

    /** 0-删除 1-正常 */
    @Excel(name = "0-删除 1-正常")
    private Integer status;
}
