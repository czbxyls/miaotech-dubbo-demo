package com.miaotech.web.controller;

import com.miaotech.api.dto.CollectUrlDTO;
import com.miaotech.api.dto.HotCollectionDTO;
import com.miaotech.api.service.CollectionFacadeService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/collect")
public class CollectionController {

    @DubboReference
    CollectionFacadeService collectionFacadeService;


    /**
     * 用户收藏的最大限制
     * @return
     */
    @RequestMapping("maxLimit")
    public int getMaxCollectionLimit() {
        return collectionFacadeService.getMaxCollectionLimit();
    }

    @RequestMapping("getHotCollections")
    public List<HotCollectionDTO> getHotCollections() {
        return collectionFacadeService.getHotCollections();
    }

    @PostMapping("collect")
    public void collect(int userId, CollectUrlDTO collectUrlDTO) {
        collectionFacadeService.collect(userId, collectUrlDTO);
    }

    @RequestMapping("view/{collectId}")
    public void view(@PathVariable  Integer collectId) {
        collectionFacadeService.view(collectId);
    }
}
