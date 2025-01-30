package com.ruoyi.framework.security.permission;

/**
 * 自定义权限注解
 *
 * @author : sinvon
 * @since :  2025/1/30 下午10:11
 */

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CustomPermission {
    String value();
}