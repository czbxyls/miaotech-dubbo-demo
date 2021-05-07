package com.miaotech.common.dlock;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class DLockAutoConfiguration {

    /**
     * 切面 拦截处理所有 @DLock
     * @return Aspect
     */
    @Bean
    public DLockAspect dLockAspect() {
        return new DLockAspect();
    }


}
