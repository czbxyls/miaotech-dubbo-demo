package com.miaotech.user.domain.repo;

import com.miaotech.user.domain.valueobject.HotCollection;

import java.util.List;

public interface CollectionRepository {

    /**
     * 获取热门收藏列表
     * @param size
     * @return
     */
    List<HotCollection> getHotCollections(int size);
}
