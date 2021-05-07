package com.miaotech.dubbo.domain.valueobject;

import lombok.Data;

/**
 * 热门链接
 */
@Data
public class HotCollection implements java.io.Serializable {

    //链接
    private String url;

    //观看次数
    private int viewCount;
}
