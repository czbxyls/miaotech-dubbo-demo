package com.miaotech.web.controller;

import com.alibaba.fastjson.JSON;
import com.miaotech.api.command.CreateOrderCmd;
import com.miaotech.api.command.OrderCmd;
import com.miaotech.api.dto.UserInfoDTO;
import com.miaotech.api.service.OrderFacadeService;
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

import javax.validation.constraints.NotEmpty;

/**
 * @Author chen
 * @Date 2021/5/14
 */
@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {

    @DubboReference
    private OrderFacadeService orderFacadeService;

    @Autowired
    LoginSession loginSession;

    /**
     * 模拟创建订单
     * @param createOrderCmd
     */
    @RequiresPermissions("user:admin")
    @PostMapping("create")
    public void createOrder(@Validated CreateOrderCmd createOrderCmd) {
        createOrderCmd.setUserId(loginSession.getUserId());
        orderFacadeService.createOrder(createOrderCmd);
    }

    /**
     * 模拟支付成功，完成订单
     * @param orderCmd
     */
    @RequiresPermissions("user:admin")
    @PostMapping("finish")
    public void finishOrder(@Validated OrderCmd orderCmd) {
        orderCmd.setUserId(loginSession.getUserId());
        orderFacadeService.finishOrder(orderCmd);
    }

}
