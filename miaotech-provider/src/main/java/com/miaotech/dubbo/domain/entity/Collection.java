package com.miaotech.dubbo.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Collection extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId
    private Integer id;

    private Integer userId;

    private String url;

}