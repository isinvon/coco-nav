package com.ruoyi.common.model.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.enums.ThemeColor;
import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.common.model.domain.bo.TypeBo;
import lombok.Data;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;

import java.util.List;

/**
 * 书签操作日志对象 coco_bookmark_log
 *
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_bookmark_log")
public class BookmarkLog extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 书签ID
     */
    @Excel(name = "书签ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long bookmarkId;

    /**
     * 操作类型
     */
    @Excel(name = "操作类型")
    private Integer action;

    /**
     * 操作详情
     */
    @Excel(name = "操作详情")
    private String actionDetails;

    /**
     * 操作人ID
     */
    @Excel(name = "操作人ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long operatorId;

    /**
     * 操作人名称
     */
    @Excel(name = "操作人名称")
    private String operatorName;

    /**
     * 操作类型 - 添加书签
     */
    public static final int BOOKMARK_LOG_ACTION_ADD = 1;

    /**
     * 操作类型 - 修改书签
     */
    public static final int BOOKMARK_LOG_ACTION_EDIT = 2;

    /**
     * 操作类型 - 删除书签
     */
    public static final int BOOKMARK_LOG_ACTION_DELETE = 3;

    /**
     * 操作类型 - 启用书签
     */
    public static final int BOOKMARK_LOG_ACTION_ENABLE = 4;

    /**
     * 操作类型 - 禁用书签
     */
    public static final int BOOKMARK_LOG_ACTION_DISABLE = 5;

    public static final List<TypeBo> bookmarkLogActionTypeList = List.of(
            new TypeBo(BOOKMARK_LOG_ACTION_ADD, "添加书签", ThemeColor.MINT_GREEN.getHexCode()),  // 深一点的薄荷绿
            new TypeBo(BOOKMARK_LOG_ACTION_EDIT, "修改书签", ThemeColor.PEACH_ORANGE.getHexCode()), // 深一点的蜜桃橙
            new TypeBo(BOOKMARK_LOG_ACTION_DELETE, "删除书签", ThemeColor.CORAL_PINK.getHexCode()), // 深一点的珊瑚粉
            new TypeBo(BOOKMARK_LOG_ACTION_ENABLE, "启用书签", ThemeColor.SKY_BLUE.getHexCode()), // 深一点的天空蓝
            new TypeBo(BOOKMARK_LOG_ACTION_DISABLE, "禁用书签", ThemeColor.LIGHT_GRAY.getHexCode()) // 深一点的浅灰色
    );
}
