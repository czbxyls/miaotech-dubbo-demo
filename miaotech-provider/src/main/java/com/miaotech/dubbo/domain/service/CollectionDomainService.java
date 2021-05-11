package com.miaotech.dubbo.domain.service;

import com.miaotech.dubbo.domain.valueobject.HotCollection;

import java.util.List;

public interface CollectionDomainService {

    /**
     * 获取热门收藏
     * @param size
     * @return
     */
    public List<HotCollection> getHotCollections(int size);
}
