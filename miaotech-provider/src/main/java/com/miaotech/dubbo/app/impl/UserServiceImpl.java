package com.miaotech.dubbo.app.impl;

import com.miaotech.dubbo.app.UserService;
import com.miaotech.dubbo.domain.entity.User;
import com.miaotech.dubbo.domain.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUser(Integer userId) {
        return userRepository.findUser(userId);
    }
}
