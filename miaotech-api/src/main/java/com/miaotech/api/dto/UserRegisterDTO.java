package com.miaotech.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Date;

@NoArgsConstructor
@Data
public class UserRegisterDTO implements java.io.Serializable {

    //用户名正则，4到16位（字母，数字，下划线，减号）
    @NotBlank(message = "用户名不能为空")
    @Size(min = 1,max = 10,message="用户名长度为4~16")
    @Pattern(regexp = "^[a-zA-Z0-9_-]{4,16}$",message = "用户名格式错误")
    private String username;

    @NotBlank(message = "手机号不能为空")
    @Size(min = 11,max = 11,message="手机号长度为11")
    @Pattern(regexp = "^1\\d{10}$",message = "手机号格式错误")
    private String phone;

    //密码强度正则，最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符
    @NotBlank(message = "密码不能为空")
    //@Size(min = 6, message="密码至少为6位")
    //@Pattern(regexp = "^.*(?=.{6,})(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$",message = "密码格式错误(至少1个大写字母，1个小写字母，1个数字，1个特殊字符)")
    private String password;

    @Email(message = "邮箱格式错误")
    private String email;

    @Past(message = "生日日期错误")
    private Date birth;

    private String nickname;


}
