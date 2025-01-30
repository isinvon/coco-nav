package com.ruoyi.project.admin.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 友链操作日志对象 coco_friendlink_log
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_friendlink_log")
public class FriendlinkLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 日志ID */
    private Long friendlinkLogId;

    /** 友链ID */
    @Excel(name = "友链ID")
    private Long friendlinkId;

    /** 操作类型 */
    @Excel(name = "操作类型")
    private Integer action;

    /** 操作人ID */
    @Excel(name = "操作人ID")
    private Long operatorId;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date logTime;
}
