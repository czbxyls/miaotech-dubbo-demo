package com.miaotech.dubbo.common;

import com.miaotech.common.cache.RedisUtil;
import com.miaotech.common.idempotent.Idempotent;
import com.miaotech.dubbo.BaseTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RedisTest  extends BaseTest {

    @Autowired
    RedisUtil redisUtil;

    @Test
    public void setTest() {
        String key = "key1";
        String value = "hello world!";
        redisUtil.set(key, value);
        Assertions.assertThat(redisUtil.get(key)).isEqualTo(value);
    }

    @Test
    @Idempotent(key="idempotentTest", ttl = 60)
    public void idempotentTest() {
        String key = "key1";
        String value = "hello world!";
        redisUtil.set(key, value);
        Assertions.assertThat(redisUtil.get(key)).isEqualTo(value);
    }
}
