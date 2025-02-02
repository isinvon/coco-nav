package com.ruoyi.common.enums;

import lombok.Getter;

/**
 * 主题色号枚举
 *
 * @author : sinvon
 * @since : 2025/2/3 上午4:21
 */
@Getter
public enum ThemeColor {
    /**
     * 深一点的薄荷绿
     */
    MINT_GREEN("#7FC8A9", "深薄荷绿"),

    /**
     * 深一点的蜜桃橙
     */
    PEACH_ORANGE("#F4A261", "深蜜桃橙"),

    /**
     * 深一点的珊瑚粉
     */
    CORAL_PINK("#E76F51", "深珊瑚粉"),

    /**
     * 深一点的天空蓝
     */
    SKY_BLUE("#457B9D", "深天空蓝"),

    /**
     * 深一点的浅灰色
     */
    LIGHT_GRAY("#B0B0B0", "深浅灰色"),

    /**
     * 深紫罗兰色
     */
    DEEP_VIOLET("#6A0572", "深紫罗兰"),

    /**
     * 深橄榄绿
     */
    DEEP_OLIVE("#556B2F", "深橄榄绿"),

    /**
     * 深藏青色
     */
    DEEP_NAVY("#1B3B6F", "深藏青"),

    /**
     * 深酒红色
     */
    DEEP_BURGUNDY("#800020", "深酒红"),

    /**
     * 深琥珀色
     */
    DEEP_AMBER("#FFBF00", "深琥珀"),

    /**
     * 深海蓝色
     */
    DEEP_SEA_BLUE("#00416A", "深海蓝"),

    /**
     * 深玫瑰红
     */
    DEEP_ROSE("#C21E56", "深玫瑰红"),

    /**
     * 深青铜色
     */
    DEEP_BRONZE("#8C7853", "深青铜"),

    /**
     * 深靛蓝色
     */
    DEEP_INDIGO("#4B0082", "深靛蓝"),

    /**
     * 深焦糖色
     */
    DEEP_CARAMEL("#8B5A2B", "深焦糖");

    private final String hexCode;
    private final String colorName;

    /**
     * 构造函数
     *
     * @param hexCode   HexCode
     * @param colorName 颜色名称
     */
    ThemeColor(String hexCode, String colorName) {
        this.hexCode = hexCode;
        this.colorName = colorName;
    }

    /**
     * 根据HexCode获取对应的主题色
     *
     * @param hexCode HexCode
     * @return ThemeColor
     */
    public static ThemeColor getByHexCode(String hexCode) {
        for (ThemeColor themeColor : values()) {
            if (themeColor.hexCode.equals(hexCode)) {
                return themeColor;
            }
        }
        return null;
    }

    /**
     * 根据颜色名称获取对应的主题色
     *
     * @param colorName 颜色名称
     * @return ThemeColor
     */
    public static ThemeColor getByColorName(String colorName) {
        for (ThemeColor themeColor : values()) {
            if (themeColor.colorName.equals(colorName)) {
                return themeColor;
            }
        }
        return null;
    }

    /**
     * 根据HexCode或颜色名称获取对应的主题色
     *
     * @param hexCodeOrColorName HexCode或颜色名称
     * @return ThemeColor
     */
    public static ThemeColor getByHexCodeOrColorName(String hexCodeOrColorName) {
        ThemeColor themeColor = getByHexCode(hexCodeOrColorName);
        if (themeColor == null) {
            themeColor = getByColorName(hexCodeOrColorName);
        }
        return themeColor;
    }

    /**
     * 根据颜色名称获取对应的HexCode
     *
     * @param colorName 颜色名称
     * @return HexCode
     */
    public static String getHexCodeByColorName(String colorName) {
        ThemeColor themeColor = getByColorName(colorName);
        if (themeColor == null) {
            return null;
        }
        return themeColor.hexCode;
    }

    /**
     * 根据HexCode获取对应的颜色名称
     *
     * @param hexCode HexCode
     * @return 颜色名称
     */
    public static String getColorNameByHexCode(String hexCode) {
        ThemeColor themeColor = getByHexCode(hexCode);
        if (themeColor == null) {
            return null;
        }
        return themeColor.colorName;
    }
}
