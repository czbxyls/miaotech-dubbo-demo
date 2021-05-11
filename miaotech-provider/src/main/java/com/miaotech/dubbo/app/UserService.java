package com.miaotech.dubbo.app;

import com.miaotech.api.command.UserRegisterCommand;
import com.miaotech.dubbo.domain.entity.User;

public interface UserService {

    User findUser(Integer userId);

    void register(UserRegisterCommand userDTO);

    int login(String username, String password);
}
