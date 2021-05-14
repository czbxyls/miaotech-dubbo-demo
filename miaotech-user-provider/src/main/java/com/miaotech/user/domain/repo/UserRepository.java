package com.miaotech.user.domain.repo;

import com.miaotech.user.domain.entity.User;

public interface UserRepository {

    /**
     * 根据用户Id查询用户
     * @param userId
     * @return
     */
    User findUser(int userId);

    /**
     * 根据用户名或者手机号查询用户
     * @param username
     * @return
     */
    User findUser(String username);

    /**
     * 新增一个用户
     * @param user
     * @return
     */
    int addUser(User user);
}
