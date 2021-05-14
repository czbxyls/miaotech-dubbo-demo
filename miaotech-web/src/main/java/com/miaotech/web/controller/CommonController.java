package com.miaotech.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
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
