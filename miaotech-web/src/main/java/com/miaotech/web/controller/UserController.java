package com.miaotech.web.controller;

import com.alibaba.fastjson.JSON;
import com.miaotech.api.dto.UserDTO;
import com.miaotech.api.dto.UserRegisterDTO;
import com.miaotech.api.service.UserFacadeService;
import com.miaotech.web.ResponseExceptionAdvice;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController  {

    @DubboReference
    private UserFacadeService userFacadeService;

    @RequestMapping("findUser/{userId}")
    public UserDTO findUser(@PathVariable Integer userId) {
        UserDTO userDTO = userFacadeService.find(userId);
        log.info("user={}", JSON.toJSONString(userDTO));
        return userDTO;
    }

    @PostMapping("register")
    public void register(@Validated UserRegisterDTO user) {
        log.info("register userDTO={}", user);
        userFacadeService.register(user);
    }

    @RequestMapping("echo/{text}")
    public String echo(@PathVariable String text) {
        log.info("echo text={}", text);
        return "echo 3: " +  text;
    }
}
