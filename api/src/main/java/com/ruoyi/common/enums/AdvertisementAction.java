package com.ruoyi.common.enums;

import lombok.Getter;

/**
 * 广告操作 枚举
 *
 * @author : sinvon
 * @since : 2025/2/2 下午11:59
 */
@Getter
public enum AdvertisementAction {
    /**
     * 添加广告
     */
    ADD(1, "添加广告"),
    /**
     * 修改广告
     */
    EDIT(2, "修改广告"),
    /**
     * 删除广告
     */
    DELETE(3, "删除广告"),
    /**
     * 启用广告
     */
    ENABLE(4, "启用广告"),
    /**
     * 禁用广告
     */
    DISABLE(5, "禁用广告");

    private final Integer code;
    private final String message;

    AdvertisementAction(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据code获取枚举
     *
     * @param code 操作码
     * @return AdvertisementAction，找不到则返回null
     */
    public static AdvertisementAction getByCode(Integer code) {
        for (AdvertisementAction value : AdvertisementAction.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

    /**
     * 根据message获取枚举
     *
     * @param message 操作名称
     * @return AdvertisementAction，找不到则返回null
     */
    public static AdvertisementAction getByMessage(String message) {
        for (AdvertisementAction value : AdvertisementAction.values()) {
            if (value.getMessage().equals(message)) {
                return value;
            }
        }
        return null;
    }

    /**
     * 根据code或message获取枚举
     *
     * @param codeOrMessage 操作码或名称
     * @return AdvertisementAction，找不到则返回null
     */
    public static AdvertisementAction getByCodeOrMessage(String codeOrMessage) {
        if (codeOrMessage == null) {
            return null;
        }
        // 先按 code 查找
        try {
            int code = Integer.parseInt(codeOrMessage);
            AdvertisementAction action = getByCode(code);
            if (action != null) {
                return action;
            }
        } catch (NumberFormatException ignored) {
            // 不是数字，直接跳过
        }
        // 再按 message 查找
        return getByMessage(codeOrMessage);
    }

    /**
     * 判断code是否存在
     *
     * @param code 操作码
     * @return 是否存在
     */
    public static boolean isExist(Integer code) {
        return getByCode(code) != null;
    }
}
