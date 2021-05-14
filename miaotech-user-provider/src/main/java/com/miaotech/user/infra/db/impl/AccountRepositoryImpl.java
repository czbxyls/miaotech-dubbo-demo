package com.miaotech.user.infra.db.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miaotech.user.domain.entity.Account;
import com.miaotech.user.domain.entity.User;
import com.miaotech.user.domain.repo.AccountRepository;
import com.miaotech.user.domain.repo.UserRepository;
import com.miaotech.user.infra.db.mapper.AccountMapper;
import com.miaotech.user.infra.db.mapper.UserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryImpl extends ServiceImpl<AccountMapper, Account>
        implements AccountRepository, IService<Account> {

    @Override
    public int decreaseAccount(Integer userId, Double amount) {
        return this.baseMapper.decreaseAccount(userId, amount);
    }

    @Override
    public Account lockAccount(Integer userId) {
        return this.baseMapper.lockAccount(userId);
    }

    @Override
    public Account findAccount(Integer userId) {
        return query().eq("user_id", userId).one();
    }

    @Override
    public int addAccount(Account account) {
        return this.baseMapper.insert(account);
    }

}
