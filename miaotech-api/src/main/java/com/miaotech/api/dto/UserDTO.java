package com.miaotech.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class UserDTO implements java.io.Serializable {

    private Integer userId;

    private String username;

    private String phone;

    private String realname;

    private Integer age;

    private String email;

}
