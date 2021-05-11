package com.miaotech.web.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class WebProperties {

    @Value("${server.response.restWrapper:true}")
    private boolean isRespWrapper;

    @Value("${server.jwt.tokenHeader:MiaoTech-Token}")
    private String JwtTokenHeader;

    @Value("${server.jwt.secret:www.miaotech.com}")
    private String jwtSecret;

    /**
     * jwt token过期时间设置
     */
    @Value("${server.jwt.expire_time}")
    private long jwtExpireTime;
}
