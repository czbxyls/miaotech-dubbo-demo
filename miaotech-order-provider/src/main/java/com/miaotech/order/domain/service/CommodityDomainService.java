package com.miaotech.order.domain.service;

import com.miaotech.api.command.CommodityCmd;
import com.miaotech.order.domain.entity.Commodity;

/**
 * @Author chen
 * @Date 2021/5/14
 */
public interface CommodityDomainService {

    /**
     * 扣减库存
     * @param commodityCmd
     */
    void decreaseStorage(CommodityCmd commodityCmd);

}
