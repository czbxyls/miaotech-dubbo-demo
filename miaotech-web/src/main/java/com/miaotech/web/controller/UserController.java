package com.miaotech.web.controller;

import com.alibaba.fastjson.JSON;
import com.miaotech.api.command.UserLoginCmd;
import com.miaotech.api.dto.UserInfoDTO;
import com.miaotech.api.command.UserRegisterCmd;
import com.miaotech.api.service.UserFacadeService;
import com.miaotech.web.common.auth.LoginSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController  {

    @DubboReference
    private UserFacadeService userFacadeService;

    @Autowired
    LoginSession loginSession;

    @RequiresPermissions("user:admin")
    @RequestMapping("findUser/{userId}")
    public UserInfoDTO findUser(@PathVariable Integer userId) {
        UserInfoDTO userInfoDTO = userFacadeService.find(userId);
        log.info("user={}", JSON.toJSONString(userInfoDTO));
        return userInfoDTO;
    }

    @PostMapping("register")
    public void register(@Validated UserRegisterCmd user) {
        log.info("register user={}", user);
        userFacadeService.register(user);
    }


    @RequestMapping("login")
    public String login(@Validated UserLoginCmd user){
        log.info("login user={}", user);
        return loginSession.saveToken(userFacadeService.login(user));
    }
}
