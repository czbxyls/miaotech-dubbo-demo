package com.miaotech.order.domain.repo;

import com.baomidou.mybatisplus.extension.service.IService;
import com.miaotech.order.domain.entity.Commodity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chen
 * @since 2021-05-14
 */
public interface CommodityRepository  {

    /**
     * 扣减库存
     * @param commodityCode
     * @param count
     */
    void decreaseStorage(String commodityCode, Integer count);

    /**
     * 锁定商品
     * @param commodityCode
     * @return
     */
    Commodity lockCommodity(String commodityCode);

    /**
     * 锁定商品
     * @param commodityCode
     * @return
     */
    Commodity findCommodity(String commodityCode);
}
