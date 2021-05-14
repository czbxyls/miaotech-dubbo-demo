package com.miaotech.api.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountCmd implements java.io.Serializable{

    //用户id
    @Min(value=1, message="用户名大于0")
    Integer userId;

    //操作金额
    @DecimalMin(value= "0.01", message="操作金额必须大于1分钱")
    Double opMoney;
}
