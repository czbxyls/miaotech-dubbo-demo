package com.miaotech.dubbo.app;

import com.miaotech.dubbo.domain.entity.User;

public interface UserService {

    User findUser(Integer userId);
}
