package com.miaotech.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class UserInfoDTO implements java.io.Serializable {

    private Integer userId;

    private String username;

    private String phone;

    private String nickname;

    private Date birth;

    private String email;

}
