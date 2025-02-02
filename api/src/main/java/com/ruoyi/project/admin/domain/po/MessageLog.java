package com.ruoyi.project.admin.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 留言操作日志对象 coco_message_log
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_message_log")
public class MessageLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 留言ID */
    @Excel(name = "留言ID")
    private Long messageId;

    /** 操作类型 */
    @Excel(name = "操作类型")
    private Integer action;

    /** 操作人ID */
    @Excel(name = "操作人ID")
    private Long operatorId;
}
