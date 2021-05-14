package com.miaotech.api.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * @Author chen
 * @Date 2021/5/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCmd implements java.io.Serializable {

    //@Min(value=1, message="用户名大于0")
    private Integer userId;

    @NotEmpty(message = "订单号不能为空")
    private String orderNo;
}
