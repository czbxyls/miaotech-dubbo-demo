package com.miaotech.dubbo.app;

import com.miaotech.dubbo.domain.valueobject.HotCollection;

import java.util.List;

public interface CollectionService {

    List<HotCollection> getHotCollections(int size);
}
