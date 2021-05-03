package com.miaotech.dubbo.infra.repo.impl;


import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miaotech.dubbo.domain.entity.User;
import com.miaotech.dubbo.domain.repo.UserRepository;
import com.miaotech.dubbo.infra.repo.mapper.UserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends ServiceImpl<UserMapper, User> implements UserRepository, IService<User> {

    @Override
    public User findUser(int userId) {
        return baseMapper.selectById(userId);
    }

}
