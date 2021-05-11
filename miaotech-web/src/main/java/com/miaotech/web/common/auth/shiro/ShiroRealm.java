package com.miaotech.web.common.auth.shiro;

import com.miaotech.api.dto.UserInfoDTO;
import com.miaotech.api.service.UserFacadeService;
import com.miaotech.common.result.Pair;
import com.miaotech.web.common.WebProperties;
import com.miaotech.web.common.auth.TokenCache;
import com.miaotech.web.common.auth.jwt.JwtToken;
import com.miaotech.web.common.auth.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

/**
 * shiro自定义认证逻辑
 *
 * @author xiaosongyue
 * @date 2021/03/30 16:52:06
 */
@Slf4j
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private TokenCache tokenCache;

    @Autowired
    private WebProperties webProperties;

    @DubboReference
    private UserFacadeService userFacadeService;

    /**
     * 设置对应的token类型
     * 必须重写此方法，不然Shiro会报错
     *
     * @param token 令牌
     * @return boolean
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 授权认证
     *
     * @param principalCollection 主要收集
     * @return {@link AuthorizationInfo}
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //权限认证
        log.info("开始进行权限认证.............");
        //获取用户名
        String token = (String) SecurityUtils.getSubject().getPrincipal();
        Integer userId = JwtUtil.getUserId(token);
        //找不到该用户
        UserInfoDTO userInfoDTO = userFacadeService.find(userId);
        if ( userInfoDTO == null ) {
            return null;
        }
        //创建授权信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //创建set集合，存储权限
        HashSet<String> rootSet = new HashSet<>();
        //添加权限
        rootSet.add("user:show");
        rootSet.add("user:admin");
        //设置权限
        info.setStringPermissions(rootSet);
        //返回权限实例
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("开始身份认证.....................");
        //获取token
        String token = (String) authenticationToken.getCredentials();
        //创建字符串，存储用户信息
        Integer userId = null;
        try {
            //获取用户名
            userId = JwtUtil.getUserId(token);
        } catch (AuthenticationException e) {
            throw new AuthenticationException("head的token拼写错误或者值为空");
        }
        if (userId == null) {
            throw new AuthenticationException("token无效");
        }
        // 校验token是否超时失效 & 或者账号密码是否错误
        Pair<Boolean, String> refreshResult = jwtTokenRefresh(token, userId, webProperties.getJwtSecret());
        if (!refreshResult.first) {
            throw new AuthenticationException("Token失效，请重新登录!");
        } else {
            token = refreshResult.second;
        }
        //返回身份认证信息
        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }

    /**
     * jwt刷新令牌
     *
     * @param token    令牌
     * @param userId 用户Id
     * @param secret 签名秘钥
     * @return boolean
     */
    public Pair<Boolean, String> jwtTokenRefresh(String token, Integer userId, String secret) {
        Pair<Boolean, String> ret = new Pair<>(false, token);
        String redisToken = tokenCache.get(userId, token);
        if (redisToken != null) {
            if (!JwtUtil.verify(redisToken, userId, secret)) {
                //Jwt过期了，则生成一个新的
                //这里通过redis的缓存时间来判断token是否失效,如果存在则刷新缓存时间，延长token有效期
                String newToken = JwtUtil.sign(userId, secret);
                //设置redis缓存，redis放更久一点
                tokenCache.save(userId, token, newToken);
                ret.setSecond(newToken);
            }
            ret.setFirst(true);
            return ret;
        }
        return ret;
    }
}
