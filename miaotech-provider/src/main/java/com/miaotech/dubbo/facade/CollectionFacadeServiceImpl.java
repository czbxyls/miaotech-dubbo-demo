package com.miaotech.dubbo.facade;

import com.miaotech.api.dto.CollectUrlDTO;
import com.miaotech.api.dto.CollectionDTO;
import com.miaotech.api.dto.HotCollectionDTO;
import com.miaotech.api.service.CollectionFacadeService;
import com.miaotech.common.converter.GeneralConvertor;
import com.miaotech.dubbo.app.CollectionService;
import com.miaotech.dubbo.common.properties.ServerConfig;
import com.miaotech.dubbo.infra.mq.producer.CollectSenderService;
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

    @Override
    public List<HotCollectionDTO> getHotCollections() {
        return generalConvertor.convertor(collectionService.getHotCollections(10), HotCollectionDTO.class);
    }

//    @Autowired
//    private CollectSenderService collectSenderService;

    @Override
    public void collect(int userId, CollectUrlDTO url) {
        //判断收藏数是否超过限制
        //新增收藏数
        //新增已一个下载消息给消息队列：下载网页，获取摘要信息等
        //collectSenderService.sendObject(url);
    }

    @Override
    public void view(int collectId) {
        //测试而已
        CollectionDTO collectionDTO = new CollectionDTO();
        collectionDTO.setUrl("https://www.google.com");
        collectionDTO.setUserId(3);
        collectionDTO.setViewCount(100);
        //collectSenderService.sendObject(collectionDTO);
    }


}
