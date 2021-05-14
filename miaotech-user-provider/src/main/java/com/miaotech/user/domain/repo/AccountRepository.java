package com.miaotech.user.domain.repo;

import com.miaotech.user.domain.entity.Account;
import com.miaotech.user.domain.valueobject.HotCollection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountRepository {

    /**
     * 用户账号扣钱
     * @param userId
     * @param amount
     * @return
     */
    int decreaseAccount(Integer userId, Double amount);

    /**
     * 查询用户账号
     * @param userId
     * @return
     */
    Account findAccount(Integer userId);


    /**
     * 锁定用户账号
     */
    Account lockAccount(Integer userId);

    /**
     * 新增账号
     * @param account
     */
    int addAccount(Account account);
}
