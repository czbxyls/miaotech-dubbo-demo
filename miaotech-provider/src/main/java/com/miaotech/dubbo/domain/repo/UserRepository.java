package com.miaotech.dubbo.domain.repo;

import com.miaotech.dubbo.domain.entity.User;

public interface UserRepository {

    User findUser(int userId);
}
