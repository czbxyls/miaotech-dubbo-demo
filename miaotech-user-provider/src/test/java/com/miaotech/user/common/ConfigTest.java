package com.miaotech.user.common;

import com.miaotech.user.BaseTest;
import com.miaotech.user.common.properties.ServerConfig;
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
