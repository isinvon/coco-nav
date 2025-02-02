package com.ruoyi.project.admin.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.enums.ThemeColor;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.project.admin.domain.bo.TypeBo;
import lombok.Data;

import java.util.List;

/**
 * 广告操作日志对象 coco_advertisement_log
 *
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_advertisement_log")
public class AdvertisementLog extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 广告ID
     */
    @Excel(name = "广告ID")
    private Long advertisementId;

    /**
     * 操作类型
     */
    @Excel(name = "操作类型")
    private Integer action;

    /**
     * 操作人ID
     */
    @Excel(name = "操作人ID")
    private Long operatorId;


    // ======================================================

    /**
     * 操作类型 - 添加广告
     */
    public static final int ADVERTISEMENT_LOG_ACTION_ADD = 1;
    /**
     * 操作类型 - 修改广告
     */
    public static final int ADVERTISEMENT_LOG_ACTION_EDIT = 2;
    /**
     * 操作类型 - 删除广告
     */
    public static final int ADVERTISEMENT_LOG_ACTION_DELETE = 3;
    /**
     * 操作类型 - 启用广告
     */
    public static final int ADVERTISEMENT_LOG_ACTION_ENABLE = 4;
    /**
     * 操作类型 - 禁用广告
     */
    public static final int ADVERTISEMENT_LOG_ACTION_DISABLE = 5;

    public static final List<TypeBo> advertisementLogActionTypeList = List.of(
            new TypeBo(ADVERTISEMENT_LOG_ACTION_ADD, "添加广告", ThemeColor.MINT_GREEN.getColorName()),  // 深一点的薄荷绿
            new TypeBo(ADVERTISEMENT_LOG_ACTION_EDIT, "修改广告", ThemeColor.PEACH_ORANGE.getColorName()), // 深一点的蜜桃橙
            new TypeBo(ADVERTISEMENT_LOG_ACTION_DELETE, "删除广告", ThemeColor.CORAL_PINK.getColorName()), // 深一点的珊瑚粉
            new TypeBo(ADVERTISEMENT_LOG_ACTION_ENABLE, "启用广告", ThemeColor.SKY_BLUE.getColorName()), // 深一点的天空蓝
            new TypeBo(ADVERTISEMENT_LOG_ACTION_DISABLE, "禁用广告", ThemeColor.LIGHT_GRAY.getColorName())  // 深一点的浅灰色
    );

}
