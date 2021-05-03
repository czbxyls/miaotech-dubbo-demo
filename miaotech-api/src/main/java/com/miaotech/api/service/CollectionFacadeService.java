package com.miaotech.api.service;

import com.miaotech.api.dto.CollectionDTO;

import java.util.List;

public interface CollectionFacadeService {

    /**
     * 获取用户的收藏列表
     * @param userId
     * @return
     */
    List<CollectionDTO> getCollectionList(int userId);


    /**
     * 用户收藏的最大限制
     * @return
     */
    public int getMaxCollectionLimit();
}
