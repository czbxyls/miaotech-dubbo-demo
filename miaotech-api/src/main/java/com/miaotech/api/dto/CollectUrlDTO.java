package com.miaotech.api.dto;

import lombok.Data;

@Data
public class CollectUrlDTO implements java.io.Serializable {
    //用户ID
    private Integer userId;
    //url信息
    private String url;
}
