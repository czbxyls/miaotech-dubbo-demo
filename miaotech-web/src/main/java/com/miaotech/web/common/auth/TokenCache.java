package com.miaotech.web.common.auth;

import com.miaotech.common.cache.RedisUtil;
import com.miaotech.common.utils.CommonUtils;
import com.miaotech.web.common.WebProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TokenCache {

    private static final String TOKEN_PREFIX = "token:";

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private WebProperties webProperties;

    public void save(Integer userId, String token, String newToken) {
        redisUtil.set(getKey(userId, token), newToken, webProperties.getJwtExpireTime() * 2 / 1000);
    }

    public void delIfAbsent(Integer userId) {
        Set<String> keys = redisUtil.keys(getKeyPrefix(userId) + "*");
        if(!CommonUtils.isEmpty(keys)) {
            for(String key : keys) redisUtil.del(key);
        }
    }

    public String get(Integer userId, String token) {
        return (String) redisUtil.get(getKey(userId, token));
    }

    public String getKey(Integer userId, String token) {
        return getKeyPrefix(userId) + token;
    }

    public String getKeyPrefix(Integer userId) {
        return TOKEN_PREFIX + userId + ":";
    }
}
