package com.miaotech.common.dlock;

import java.lang.annotation.*;

/**
 * 主要参考https://github.com/pig-mesh/idempotent-spring-boot-starter进行改造(感谢作者)
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface DLock {

    /**
     * 分布式锁操作所在组
     * @return
     */
    String group() default "default";

    /**
     * 分布式锁操作的唯一标识，使用spring el表达式 用#来引用方法参数
     * @return Spring-EL expression
     */
    String key() default "";

    /**
     * 有效期 默认：10 有效期要大于程序执行时间，否则请求还是可能会进来
     * 单位：second
     * @return expireTime
     */
    int timeout() default 10;


}