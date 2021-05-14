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
public class CommodityCmd implements java.io.Serializable{

    /**
     * 商品编号
     */
    @NotEmpty(message = "商品编码不能为空")
    private String commodityCode;

    /**
     * 操作数量
     */
    @Min(value=1, message="数量大于0")
    private Integer opCount;

}
