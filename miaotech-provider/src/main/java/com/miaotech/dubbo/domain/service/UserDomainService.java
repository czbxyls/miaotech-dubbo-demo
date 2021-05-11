package com.miaotech.dubbo.domain.service;

import com.miaotech.api.command.UserRegisterCommand;
import com.miaotech.dubbo.domain.entity.User;

public interface UserDomainService {

    /**
     * 查找一个用户信息
     * @param userId
     * @return
     */
    public User findUser(Integer userId);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    int login(String username, String password);

    /**
     * 用户注册
     * @param userDTO
     */
    void register(UserRegisterCommand userDTO);
}
