package com.miaotech.api.service;

import com.miaotech.api.dto.CollectUrlDTO;
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
    int getMaxCollectionLimit();


    /**
     * 获取热门收藏
     * @return
     */
    List<HotCollectionDTO> getHotCollections();

    /**
     * 收藏一个网页
     * @param userId
     * @param url
     */
    void collect(int userId, CollectUrlDTO url);

    /**
     * 浏览一个网页
     * @param collectId
     */
    void view(int collectId);

}
