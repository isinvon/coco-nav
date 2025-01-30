package com.ruoyi.project.admin.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * VIP会员对象 coco_vip
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_vip")
public class Vip extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** VIP记录ID */
    private Long vipId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** VIP类型ID */
    @Excel(name = "VIP类型ID")
    private Long vipTypeId;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 0-失效 1-有效 */
    @Excel(name = "0-失效 1-有效")
    private Integer status;
}
