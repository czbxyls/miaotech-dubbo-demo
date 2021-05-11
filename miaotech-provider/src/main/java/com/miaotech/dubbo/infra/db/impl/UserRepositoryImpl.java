package com.miaotech.dubbo.infra.db.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miaotech.dubbo.domain.entity.User;
import com.miaotech.dubbo.domain.repo.UserRepository;
import com.miaotech.dubbo.infra.db.mapper.UserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends ServiceImpl<UserMapper, User> implements UserRepository, IService<User> {

    @Override
    public User findUser(int userId) {
        return baseMapper.selectById(userId);
    }

    @Override
    public User findUser(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).or().eq("phone", username);
        return baseMapper.selectOne(queryWrapper);
    }
}
