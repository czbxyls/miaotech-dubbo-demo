package com.miaotech.dubbo.common;

import com.miaotech.dubbo.BaseTest;
import com.miaotech.dubbo.common.properties.ServerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ConfigTest extends BaseTest  {

    @Autowired
    ServerConfig config;

    @Test
    void testUserSessionTime() {
        int time = config.getUserSessionTime();
        System.out.println(time);
    }
}
