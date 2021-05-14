package com.miaotech.api.command;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author chen
 * @Date 2021/5/14
 */
@Data
public class CreateOrderCmd implements java.io.Serializable{

    //@Min(value=1, message="用户名大于0")
    private Integer userId;

    @NotEmpty(message = "商品编码不能为空")
    private String commodityCode;

    @Min(value=1, message="订单数必须大于0")
    private int count;

    @DecimalMin(value= "0.01", message="订单金额必须大于1分钱")
    private double money;
}
