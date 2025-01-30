package com.ruoyi.project.admin.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 积分流水对象 coco_point_log
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
public class PointLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 日志ID */
    private Long pointLogId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 1-增加 2-扣除 3-冻结 */
    @Excel(name = "1-增加 2-扣除 3-冻结")
    private Integer changeType;

    /** 变动数值 */
    @Excel(name = "变动数值")
    private Integer amount;

    /** 变动后积分余额 */
    @Excel(name = "变动后积分余额")
    private Integer balance;

    /** 来源模块 */
    @Excel(name = "来源模块")
    private String source;

    /** 操作类型 */
    @Excel(name = "操作类型")
    private Integer action;

    /** 变动说明 */
    @Excel(name = "变动说明")
    private String description;
}
