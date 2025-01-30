package com.ruoyi.project.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 广告操作日志对象 coco_advertisement_log
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_advertisement_log")
public class AdvertisementLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 日志ID */
    private Long advertisementLogId;

    /** 广告ID */
    @Excel(name = "广告ID")
    private Long advertisementId;

    /** 操作类型 */
    @Excel(name = "操作类型")
    private Integer action;

    /** 操作人ID */
    @Excel(name = "操作人ID")
    private Long operatorId;
}
