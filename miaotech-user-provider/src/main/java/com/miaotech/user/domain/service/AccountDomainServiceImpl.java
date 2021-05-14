package com.miaotech.user.domain.service;

import com.miaotech.api.command.AccountCmd;
import com.miaotech.common.MsgException;
import com.miaotech.user.domain.entity.Account;
import com.miaotech.user.domain.repo.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class AccountDomainServiceImpl implements AccountDomainService {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * 扣用户钱
     */
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public void decreaseAccount(AccountCmd accountCmd) {
        Account account = accountRepository.lockAccount(accountCmd.getUserId());
        if(account == null) {
            throw MsgException.newMessageException("用户不存在，userId=" + account.getUserId());
        }
        if(account.getAmount()< accountCmd.getOpMoney()) {
            throw MsgException.newMessageException("当前账户余额不足");
        }
        int ret = accountRepository.decreaseAccount(accountCmd.getUserId(), accountCmd.getOpMoney());
        if(ret <= 0) throw MsgException.newMessageException("扣除账户失败");
    }
}
