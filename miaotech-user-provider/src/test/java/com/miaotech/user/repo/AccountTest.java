package com.miaotech.user.repo;

import com.alibaba.fastjson.JSON;
import com.miaotech.user.BaseTest;
import com.miaotech.user.domain.entity.Account;
import com.miaotech.user.domain.repo.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author chen
 * @Date 2021/5/14
 */
public class AccountTest extends BaseTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void lockTest() {
        Account account = accountRepository.lockAccount(1);
        System.out.println(JSON.toJSONString(account));
    }

    @Test
    public void findTest() {
        Account account = accountRepository.findAccount(1);
        System.out.println(JSON.toJSONString(account));
    }
}
