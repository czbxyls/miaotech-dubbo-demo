package com.miaotech.dubbo.facade;

import com.miaotech.api.dto.UserDTO;
import com.miaotech.api.service.UserFacadeService;
import com.miaotech.dubbo.app.UserService;
import com.miaotech.common.converter.GeneralConvertor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@DubboService(timeout = 3000)
public class UserFacadeServiceImpl implements UserFacadeService {

    @Autowired
    UserService userService;

    @Autowired
    GeneralConvertor generalConvertor;

    @Override
    public UserDTO find(Integer userId) {
        log.info("find userId {}", userId);
        return generalConvertor.convertor(userService.findUser(userId), UserDTO.class);
    }
}
