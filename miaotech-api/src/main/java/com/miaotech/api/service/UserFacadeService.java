package com.miaotech.api.service;

import com.miaotech.api.dto.UserDTO;

public interface UserFacadeService {

    /**
     * 通过ID获取用户
     * @param userId
     * @return
     */
    UserDTO find(Integer userId);
}
