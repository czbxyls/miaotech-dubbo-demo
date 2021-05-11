package com.miaotech.dubbo.domain.service;

import com.miaotech.api.command.UserRegisterCommand;
import com.miaotech.common.MsgException;
import com.miaotech.dubbo.domain.entity.User;
import com.miaotech.dubbo.domain.repo.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDomainServiceImpl implements UserDomainService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUser(Integer userId) {
        return userRepository.findUser(userId);
    }

    @Override
    public void register(UserRegisterCommand userDTO) {

    }

    @Override
    public int login(String username, String password) {
        User user = userRepository.findUser(username);
        if(user == null) throw MsgException.newMessageException("用户名或密码错误! 请重试");
        if(!StringUtils.equals(user.getPassword(), password)) throw MsgException.newMessageException("用户名或密码错误! 请重试");
        return user.getId();
    }
}
