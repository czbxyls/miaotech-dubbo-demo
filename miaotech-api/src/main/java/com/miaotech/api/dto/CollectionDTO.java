package com.miaotech.api.dto;

import lombok.Data;

@Data
public class CollectionDTO implements java.io.Serializable{
    //用户id
    private Integer userId;
    //url
    private String url;
    //浏览次数
    private int viewCount;
}
