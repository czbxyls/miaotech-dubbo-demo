package com.miaotech.user.domain.service;

import com.miaotech.user.domain.entity.User;

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
     * @param user
     */
    void register(User user);
}
