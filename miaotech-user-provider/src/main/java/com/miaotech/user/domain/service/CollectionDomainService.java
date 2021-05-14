package com.miaotech.user.domain.service;

import com.miaotech.user.domain.valueobject.HotCollection;

import java.util.List;

public interface CollectionDomainService {

    /**
     * 获取热门收藏
     * @param size
     * @return
     */
    public List<HotCollection> getHotCollections(int size);
}
