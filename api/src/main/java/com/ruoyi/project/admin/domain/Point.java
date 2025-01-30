package com.ruoyi.project.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 积分账户对象 coco_point
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_point")
public class Point extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 积分账户ID */
    private Long pointId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 当前积分 */
    @Excel(name = "当前积分")
    private Integer balance;
}
