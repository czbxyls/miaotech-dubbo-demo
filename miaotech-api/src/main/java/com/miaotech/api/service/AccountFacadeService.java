package com.miaotech.api.service;

import com.miaotech.api.command.AccountCmd;

public interface AccountFacadeService {

    /**
     * 扣除账户金额
     * @param accountCmd
     */
    void decreaseAccount(AccountCmd accountCmd);
}
