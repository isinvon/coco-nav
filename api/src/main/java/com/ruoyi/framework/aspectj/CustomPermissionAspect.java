package com.ruoyi.framework.aspectj;

import com.ruoyi.framework.security.permission.CustomPermission;
import com.ruoyi.framework.security.service.PermissionService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 自定义权限AOP注解切面
 *
 * @author : sinvon
 * @since :  2025/1/30 下午10:11
 */

@Aspect
@Component
public class CustomPermissionAspect {

    @Autowired
    private PermissionService permissionService;

    @Before("@annotation(com.ruoyi.framework.security.permission.CustomPermission)")
    public void checkPermission(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        CustomPermission customPermission = method.getAnnotation(CustomPermission.class);
        if (customPermission != null) {
            String permission = customPermission.value();
            if (!permissionService.hasPermi(permission)) {
                // 这里可以抛出一个自定义的权限异常，用于统一处理
                throw new RuntimeException("没有权限访问该资源");
            }
        }
    }
}