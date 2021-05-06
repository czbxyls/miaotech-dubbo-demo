package com.miaotech.dubbo.facade;

import com.miaotech.api.dto.CollectionDTO;
import com.miaotech.api.dto.HotCollectionDTO;
import com.miaotech.api.service.CollectionFacadeService;
import com.miaotech.common.converter.GeneralConvertor;
import com.miaotech.dubbo.app.CollectionService;
import com.miaotech.dubbo.common.properties.ServerConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
@DubboService(timeout = 3000)
public class CollectionFacadeServiceImpl implements CollectionFacadeService {

    @Autowired
    ServerConfig config;

    @Autowired
    CollectionService collectionService;

    @Autowired
    GeneralConvertor generalConvertor;

    @Override
    public List<CollectionDTO> getCollectionList(int userId) {
        return null;
    }

    @Override
    public int getMaxCollectionLimit() {
        log.info("request getMaxCollectionLimit()");
        return config.getMaxCollectionLimit();
    }

    // 原本的业务方法.
    @Override
    public List<HotCollectionDTO> getHotCollections() {
        return generalConvertor.convertor(collectionService.getHotCollections(10), HotCollectionDTO.class);
    }


}
