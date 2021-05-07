package com.miaotech.dubbo.app.impl;

import com.miaotech.dubbo.app.CollectionService;
import com.miaotech.dubbo.domain.repo.CollectionRepository;
import com.miaotech.dubbo.domain.valueobject.HotCollection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CollectionServiceImpl implements CollectionService  {

    @Autowired
    private CollectionRepository collectionRepository;

    @Cacheable(cacheNames = "cache_HotCollection", key="'hc_' + #size")
    public List<HotCollection> getHotCollections(int size) {
        log.info("read data from repository!");
        return collectionRepository.getHotCollections(size);
    }
}
