package com.miaotech.dubbo.infra.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Arrays;


/**
 * redis配置类
 * 源自：https://blog.csdn.net/u010957645/article/details/89340983
 * @author gitonline
 *
 */

@Configuration
@EnableCaching
//
public class RedisConfiguration {

    /**
     * springboot2.x 使用LettuceConnectionFactory 代替 RedisConnectionFactory
     * application.yml配置基本信息后,springboot2.x  RedisAutoConfiguration能够自动装配
     * LettuceConnectionFactory 和 RedisConnectionFactory 及其 RedisTemplate
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    /**
     * 缓存的Key生成器
     * @return
     */
    @Bean("keyGenerator")
    public KeyGenerator keyGenerator(){
        return new KeyGenerator() {

            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName()).append(".");
                sb.append(method.getName());
                sb.append("[" + Arrays.asList(params).toString() + "]");
                return sb.toString();
            }
        };
    }

    /**
     * 配置一个CacheManager才能使用@Cacheable等注解
     *
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate<String, Object> template) {

        RedisCacheManager redisCacheManager =
                RedisCacheManager.RedisCacheManagerBuilder
                        // Redis 连接工厂
                        .fromConnectionFactory(template.getConnectionFactory())
                        .cacheDefaults(getCacheConfigurationWithTtl(template, 60 * 60)) //TODO 这里实现上跟业务耦合了
                        .withCacheConfiguration("cache_user", getCacheConfigurationWithTtl(template, 60))
                        .withCacheConfiguration("cache_HotCollection", getCacheConfigurationWithTtl(template, 30))
                        // 配置同步修改或删除 put/evict
                        .transactionAware()
                        //在spring提交事务之前不缓存
                        .build();

        return redisCacheManager;
    }

    RedisCacheConfiguration getCacheConfigurationWithTtl(RedisTemplate<String, Object> template, long seconds) {

        return RedisCacheConfiguration
                .defaultCacheConfig()
                // 设置key为String
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(template.getStringSerializer()))
                // 设置value 为自动转Json的Object
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(template.getValueSerializer()))
                // 不缓存null
                .disableCachingNullValues()
                // 缓存数据保存1小时
                .entryTtl(Duration.ofSeconds(seconds));
    }

}