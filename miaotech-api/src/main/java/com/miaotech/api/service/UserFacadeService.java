package com.miaotech.api.service;

import com.miaotech.api.command.UserLoginCmd;
import com.miaotech.api.dto.UserInfoDTO;
import com.miaotech.api.command.UserRegisterCmd;

public interface UserFacadeService {

    /**
     * 通过ID获取用户
     * @param userId
     * @return
     */
    UserInfoDTO find(Integer userId);


    /**
     * 注册一个用户
     * @param userDTO
     */
    void register(UserRegisterCmd userDTO);


    /**
     * 用户登录校验
     * @param user
     */
    int login(UserLoginCmd user);
}
