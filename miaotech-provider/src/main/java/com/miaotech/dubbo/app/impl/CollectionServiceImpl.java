package com.miaotech.dubbo.app.impl;

import com.miaotech.dubbo.app.CollectionService;
import com.miaotech.dubbo.domain.entity.Collection;
import com.miaotech.dubbo.domain.repo.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService  {

    @Autowired
    private CollectionRepository collectionRepository;

    public List<Collection> getHotCollections(int size) {
        return collectionRepository.getHotCollections(size);
    }
}
