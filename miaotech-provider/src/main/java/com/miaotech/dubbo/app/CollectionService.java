package com.miaotech.dubbo.app;

import com.miaotech.dubbo.domain.entity.Collection;

import java.util.List;

public interface CollectionService {

    List<Collection> getHotCollections(int size);
}
