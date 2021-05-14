package com.miaotech.user.infra.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miaotech.user.domain.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {

    /**
     * 扣除金额
     * @param userId
     * @param amount
     * @return
     */
    int decreaseAccount(@Param("userId") Integer userId, @Param("amount") Double amount);


    /**
     * 锁定账户
     * @param userId
     * @return
     */
    Account lockAccount(@Param("userId") Integer userId);
}


