package com.ruoyi.project.admin.domain.po;

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

    /** 友链ID */
    @Excel(name = "友链ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long friendlinkId;

    /** 操作类型 */
    @Excel(name = "操作类型")
    private Integer action;

    /**
     * 操作详情
     */
    @Excel(name = "操作详情")
    private String actionDetails;

    /** 操作人ID */
    @Excel(name = "操作人ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long operatorId;

    /**
     * 操作人名称
     */
    @Excel(name = "操作人名称")
    private String operatorName;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date logTime;
}
