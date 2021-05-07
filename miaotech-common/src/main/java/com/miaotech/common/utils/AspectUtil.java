package com.miaotech.common.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

public class AspectUtil {


    /**
     * 根据切点解析方法信息
     * @param joinPoint 切点信息
     * @return Method 原信息
     */
    public static Method getMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.getDeclaringClass().isInterface()) {
            try {
                method = joinPoint.getTarget().getClass().
                        getDeclaredMethod(joinPoint.getSignature().getName(),
                                method.getParameterTypes());
            }
            catch (SecurityException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return method;
    }
}
