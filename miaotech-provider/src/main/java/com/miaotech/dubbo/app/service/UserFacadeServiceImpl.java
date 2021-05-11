package com.miaotech.dubbo.app.service;

import com.miaotech.api.command.UserLoginCommand;
import com.miaotech.api.command.UserRegisterCommand;
import com.miaotech.api.dto.UserInfoDTO;
import com.miaotech.api.service.UserFacadeService;
import com.miaotech.common.converter.GeneralConvertor;
import com.miaotech.common.dlock.DLock;
import com.miaotech.dubbo.domain.service.UserDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@DubboService(timeout = 3000)
public class UserFacadeServiceImpl implements UserFacadeService {

    @Autowired
    UserDomainService userDomainService;

    @Autowired
    GeneralConvertor generalConvertor;

    @Override
    //@Idempotent(timeout = 20, info = "这只是用来测试Idempotent：请勿重复查询")
    public UserInfoDTO find(Integer userId) {
        log.info("find userId {}", userId);
        return generalConvertor.convertor(userDomainService.findUser(userId), UserInfoDTO.class);
    }

    @Override
    @DLock(group = "user", key = "#userDTO.phone")
    public void register(UserRegisterCommand userDTO) {
        try {
            log.info("模拟用户注册，耗时工作");
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            log.error("interrupted! ", ex);
        }
    }

    @Override
    public int login(UserLoginCommand user) {
        return userDomainService.login(user.getUsername(), user.getPassword());
    }
}
