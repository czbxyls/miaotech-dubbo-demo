package com.miaotech.dubbo.common;

import com.miaotech.dubbo.BaseTest;
import com.miaotech.dubbo.infra.cache.RedisUtil;
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
}
