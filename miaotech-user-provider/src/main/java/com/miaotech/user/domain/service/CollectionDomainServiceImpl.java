package com.miaotech.user.domain.service;

import com.miaotech.user.domain.repo.CollectionRepository;
import com.miaotech.user.domain.valueobject.HotCollection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CollectionDomainServiceImpl implements CollectionDomainService {

    private CollectionRepository collectionRepository;

    @Override
    public List<HotCollection> getHotCollections(int size) {
        log.info("read data from repository!");
        return collectionRepository.getHotCollections(size);
    }
}
