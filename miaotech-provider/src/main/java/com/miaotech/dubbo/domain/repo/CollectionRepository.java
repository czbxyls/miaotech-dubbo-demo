package com.miaotech.dubbo.domain.repo;

import com.miaotech.dubbo.domain.valueobject.HotCollection;

import java.util.List;

public interface CollectionRepository {
    List<HotCollection> getHotCollections(int size);
}
