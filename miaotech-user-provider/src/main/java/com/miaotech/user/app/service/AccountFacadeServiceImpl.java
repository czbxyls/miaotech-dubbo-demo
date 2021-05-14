package com.miaotech.user.app.service;

import com.miaotech.api.command.AccountCmd;
import com.miaotech.api.service.AccountFacadeService;
import com.miaotech.user.domain.service.AccountDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@DubboService(timeout = 3000)
public class AccountFacadeServiceImpl implements AccountFacadeService {


    @Autowired
    private AccountDomainService accountDomainService;

    @Override
    public void decreaseAccount(AccountCmd accountCmd) {
        log.info("请求账户微服务：{}", accountCmd.toString());
        accountDomainService.decreaseAccount(accountCmd);
    }
}
