package com.miaotech.user.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.miaotech.common.entity.BaseEntity;
import lombok.Data;

@Data
public class Account extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Double amount;

    @Override
    public String toString() {
        return "Account{" +
                ", id=" + id +
                ", userId=" + userId +
                ", amount=" + amount +
                "}";
    }
}
