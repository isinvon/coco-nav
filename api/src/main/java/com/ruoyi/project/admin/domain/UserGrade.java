package com.ruoyi.project.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 用户等级对象 coco_user_grade
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_user_grade")
public class UserGrade extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 等级ID */
    private Long userGradeId;

    /** 等级名称 */
    @Excel(name = "等级名称")
    private String gradeName;

    /** 最低积分要求 */
    @Excel(name = "最低积分要求")
    private Integer minPoints;

    /** 等级特权描述 */
    @Excel(name = "等级特权描述")
    private String privileges;

    /** 等级图标 */
    @Excel(name = "等级图标")
    private String icon;
}
