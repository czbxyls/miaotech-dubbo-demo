package com.miaotech.user.domain.factory;

import com.miaotech.user.domain.entity.Account;
import com.miaotech.user.domain.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountFactory {
    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(int userId) {
        Account account = new Account();
        account.setAmount(0.0);
        account.setUserId(userId);
        int id = accountRepository.addAccount(account);
        account.setId(id);
        return account;
    }

}
