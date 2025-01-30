package com.ruoyi.project.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * VIP操作日志对象 coco_vip_log
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_vip_log")
public class VipLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 日志ID */
    private Long vipLogId;

    /** VIP记录ID */
    @Excel(name = "VIP记录ID")
    private Long vipId;

    /** 操作类型 */
    @Excel(name = "操作类型")
    private Integer action;

    /** VIP时长 (秒) */
    @Excel(name = "VIP时长 (秒)")
    private Long duration;

    /** 操作人ID */
    @Excel(name = "操作人ID")
    private Long operatorId;
}
