package com.miaotech.common.idempotent;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class IdempotentAutoConfiguration {

    /**
     * 切面 拦截处理所有 @Idempotent
     * @return Aspect
     */
    @Bean
    public IdempotentAspect idempotentAspect() {
        return new IdempotentAspect();
    }

}
