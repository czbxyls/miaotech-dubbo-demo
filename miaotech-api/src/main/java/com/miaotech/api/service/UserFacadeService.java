package com.miaotech.api.service;

import com.miaotech.api.dto.UserDTO;
import com.miaotech.api.dto.UserRegisterDTO;

public interface UserFacadeService {

    /**
     * 通过ID获取用户
     * @param userId
     * @return
     */
    UserDTO find(Integer userId);


    /**
     * 注册一个用户
     * @param userDTO
     */
    void register(UserRegisterDTO userDTO);
}
