package com.miaotech.dubbo.domain.repo;

import com.miaotech.api.dto.CollectionDTO;
import com.miaotech.dubbo.domain.entity.Collection;

import java.util.List;

public interface CollectionRepository {
    List<Collection> getHotCollections(int size);
}
