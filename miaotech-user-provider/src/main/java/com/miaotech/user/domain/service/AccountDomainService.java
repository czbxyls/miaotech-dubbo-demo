package com.miaotech.user.domain.service;

import com.miaotech.api.command.AccountCmd;

public interface AccountDomainService {

    /**
     * 扣用户钱
     */
    void decreaseAccount(AccountCmd accountCmd);
}
