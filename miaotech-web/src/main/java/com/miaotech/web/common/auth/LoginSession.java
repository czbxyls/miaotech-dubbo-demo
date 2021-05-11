package com.miaotech.web.common.auth;

import com.miaotech.web.common.WebProperties;
import com.miaotech.web.common.auth.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
@Slf4j
public class LoginSession {

    @Autowired
    WebProperties webProperties;

    @Autowired
    TokenCache tokenCache;

    /**
     * 根据token获取用户ID
     * @return
     */
    public Integer getUserId() {
        String token = (String) SecurityUtils.getSubject().getPrincipal();
        return  JwtUtil.getUserId(token);
    }

    /**
     * 获取用户token
     * @return
     */
    public String getToken() {
        return (String) SecurityUtils.getSubject().getPrincipal();
    }


    /**
     * 保存token
     * @return
     */
    public String saveToken(int userId){
        tokenCache.delIfAbsent(userId);
        String token = JwtUtil.sign(userId, webProperties.getJwtSecret());
        tokenCache.save(userId, token, token);
        return token;
    }
}
