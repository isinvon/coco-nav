package com.ruoyi.project.admin.domain.bo;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类型Bo
 *
 * @author sinvon
 * @since 2025年2月3日01:29:43
 */

public record TypeBo(Integer value, String label, String color) {

    public static TypeBo $(Integer value, String label) {
        return $(value, label, null);
    }

    public static TypeBo $(Integer value, String label, String color) {
        return new TypeBo(value, label, color);
    }

    public static TypeBo build(Integer value, String title) {
        return build(value, title, null);
    }

    public static TypeBo build(Integer value, String title, String color) {
        return new TypeBo(value, title, color);
    }

    /**
     * 将typeBo List转为 typeMap
     */
    public static Map<Integer, String> typeMap(List<TypeBo> typeName) {
        HashMap<Integer, String> map = new HashMap<>();
        typeName.forEach(t -> map.put(t.value, t.label));
        return Map.copyOf(map);
    }
}
