package com.miaotech.web.controller;

import com.miaotech.api.service.CollectionFacadeService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
