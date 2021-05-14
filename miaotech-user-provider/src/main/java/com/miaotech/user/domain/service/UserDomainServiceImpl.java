package com.miaotech.user.domain.service;

import com.miaotech.common.MsgException;
import com.miaotech.user.domain.entity.User;
import com.miaotech.user.domain.factory.AccountFactory;
import com.miaotech.user.domain.repo.AccountRepository;
import com.miaotech.user.domain.repo.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserDomainServiceImpl implements UserDomainService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountFactory accountFactory;

    @Override
    public User findUser(Integer userId) {
        return userRepository.findUser(userId);
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public void register(User user) {
        User dbUser = userRepository.findUser(user.getUsername());
        if(dbUser != null)
            throw MsgException.newMessageException("用户名已存在");
        dbUser = userRepository.findUser(user.getPhone());
        if(dbUser != null)
            throw MsgException.newMessageException("手机号已存在");
        user.setAddtime(new Date());
        userRepository.addUser(user);
        accountFactory.createAccount(user.getId());
    }

    @Override
    public int login(String username, String password) {
        User user = userRepository.findUser(username);
        if(user == null) throw MsgException.newMessageException("用户名或密码错误! 请重试");
        if(!StringUtils.equals(user.getPassword(), password)) throw MsgException.newMessageException("用户名或密码错误! 请重试");
        return user.getId();
    }
}
