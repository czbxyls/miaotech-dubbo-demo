package com.miaotech.user.app.service;

import com.miaotech.api.command.UserLoginCmd;
import com.miaotech.api.command.UserRegisterCmd;
import com.miaotech.api.dto.UserInfoDTO;
import com.miaotech.api.service.UserFacadeService;
import com.miaotech.common.converter.GeneralConvertor;
import com.miaotech.common.dlock.DLock;
import com.miaotech.common.utils.BeanUtils;
import com.miaotech.user.domain.entity.User;
import com.miaotech.user.domain.service.UserDomainService;
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
    public void register(UserRegisterCmd userDTO) {
        log.info("模拟用户注册，耗时工作");
        User user = BeanUtils.transform(userDTO, User.class);
        userDomainService.register(user);
    }

    @Override
    public int login(UserLoginCmd user) {
        return userDomainService.login(user.getUsername(), user.getPassword());
    }
}
