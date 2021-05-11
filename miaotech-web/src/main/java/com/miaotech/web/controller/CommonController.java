package com.miaotech.web.controller;

import com.alibaba.fastjson.JSON;
import com.miaotech.api.command.UserLoginCommand;
import com.miaotech.api.command.UserRegisterCommand;
import com.miaotech.api.dto.UserInfoDTO;
import com.miaotech.api.service.UserFacadeService;
import com.miaotech.web.common.auth.LoginSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CommonController {

    @RequestMapping("echo/{text}")
    public String echo(@PathVariable String text) {
        log.info("echo text={}", text);
        return "echo 3: " +  text;
    }

    @RequestMapping("/login")
    public String reqLogin() {
        return "请登录";
    }

}
