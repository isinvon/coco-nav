package com.ruoyi.project.admin.domain.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.enums.ThemeColor;
import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.project.admin.domain.bo.TypeBo;
import lombok.Data;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;

import java.util.List;

/**
 * 书签管理对象 coco_bookmark
 *
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_bookmark")
public class Bookmark extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 网站标题 */
    @Excel(name = "网站标题")
    private String title;

    /** 网站地址 */
    @Excel(name = "网站地址")
    private String url;

    /** 分类ID */
    @Excel(name = "分类ID")
    private Long bookmarkCategoryId;

    /** 网站图标 */
    @Excel(name = "网站图标")
    private String icon;

    /** 点击次数 */
    @Excel(name = "点击次数")
    private Integer clickCount;

    /** 排序值 */
    @Excel(name = "排序值")
    private Integer sortOrder;

    /** 0-禁用 1-启用 */
    @Excel(name = "0-禁用 1-启用")
    private Integer status;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableLogic
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    /**
     * 书签上架状态  禁用 - 0
     */
    public static final int BOOKMARK_STATUS_DISABLED = 0;

    /**
     * 书签上架状态  启用 - 1
     */
    public static final int BOOKMARK_STATUS_ENABLED = 1;

    /**
     *  书签删除状态 正常 - 0
     */
    public static final int BOOKMARK_DELETE_STATUS_NORMAL = 0;

    /**
     *  书签删除状态 删除 - 1
     */
    public static final int BOOKMARK_DELETE_STATUS_DELETED = 1;

    /**
     *  网站图标
     */
    public static final String BOOKMARK_ICON_DEFAULT = "https://element.eleme.io/favicon.ico";

    public static final List<TypeBo> bookmarkStatusTypeList = List.of(
            new TypeBo(BOOKMARK_STATUS_DISABLED, "已禁用", ThemeColor.CORAL_PINK.getHexCode()),
            new TypeBo(BOOKMARK_STATUS_ENABLED, "已启用", ThemeColor.MINT_GREEN.getHexCode())
    );

    public static final List<TypeBo> bookmarkDeleteStatusList = List.of(
            new TypeBo(BOOKMARK_DELETE_STATUS_DELETED, "删除", ThemeColor.CORAL_PINK.getHexCode()),
            new TypeBo(BOOKMARK_DELETE_STATUS_NORMAL, "正常", ThemeColor.MINT_GREEN.getHexCode())
    );
}
