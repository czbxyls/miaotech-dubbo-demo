package com.miaotech.api.service;

import com.miaotech.api.dto.CollectionDTO;
import com.miaotech.api.dto.HotCollectionDTO;

import java.util.List;

public interface CollectionFacadeService {

    /**
     * 获取用户的收藏列表
     * @param userId
     * @return
     */
    List<CollectionDTO> getCollectionList(int userId);


    /**
     * 单个用户收藏的最大限制
     * （此用例用来测试基于nacos的配置中心）
     * @return
     */
    public int getMaxCollectionLimit();


    /**
     *
     * @return
     */
    List<HotCollectionDTO> getHotCollections();
}
