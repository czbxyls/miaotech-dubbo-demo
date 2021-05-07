package com.miaotech.common.idempotent;

import java.lang.annotation.*;

/**
 * 主要参考https://github.com/pig-mesh/idempotent-spring-boot-starter进行改造(感谢作者)
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Idempotent {

    /**
     * 幂等操作的唯一标识，使用spring el表达式 用#来引用方法参数
     * @return Spring-EL expression
     */
    String key();

    /**
     * 有效期 默认：1 有效期要大于程序执行时间，否则请求还是可能会进来
     * 单位：second
     * @return expireTime
     */
    int ttl() default 1;

    /**
     * 提示信息，可自定义
     * @return String
     */
    String info() default "收到重复请求，请稍后重试";

    /**
     * 是否在业务完成后删除key true:删除 false:不删除
     * @return boolean
     */
    boolean isAutoDel() default false;

}