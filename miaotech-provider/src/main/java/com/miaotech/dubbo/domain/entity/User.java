package com.miaotech.dubbo.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dozer.Mapping;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId
    @Mapping("userId")
    private Integer id;

    private String username;

    private String password;

    private String phone;

    private String realname;

    private Integer age;

    private String email;

    private Date addtime;


}