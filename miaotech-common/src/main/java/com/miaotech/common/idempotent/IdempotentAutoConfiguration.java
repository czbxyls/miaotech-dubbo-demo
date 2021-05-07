package com.miaotech.common.idempotent;

import com.miaotech.common.idempotent.util.IdempotentKeyGenerator;
import com.miaotech.common.idempotent.util.IdempotentKeyResolver;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
    /**
     * key 解析器
     * @return KeyResolver
     */
    @Bean
    @ConditionalOnMissingBean(IdempotentKeyResolver.class)
    public IdempotentKeyResolver idempotentKeyResolver() {
        return new IdempotentKeyResolver();
    }

    /**
     * key 解析器
     * @return KeyResolver
     */
    @Bean
    @ConditionalOnMissingBean(IdempotentKeyGenerator.class)
    public IdempotentKeyGenerator idempotentKeyGenerator() {
        return new IdempotentKeyGenerator();
    }
}
