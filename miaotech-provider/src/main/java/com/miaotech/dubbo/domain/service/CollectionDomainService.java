package com.miaotech.dubbo.domain.service;

import com.miaotech.dubbo.domain.repo.CollectionRepository;
import com.miaotech.dubbo.domain.valueobject.HotCollection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CollectionDomainService {

    private CollectionRepository collectionRepository;

    public List<HotCollection> getHotCollections(int size) {
        log.info("read data from repository!");
        return collectionRepository.getHotCollections(size);
    }
}
