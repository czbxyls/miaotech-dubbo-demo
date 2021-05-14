package com.miaotech.order.infra.db.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miaotech.order.domain.entity.Commodity;
import com.miaotech.order.domain.repo.CommodityRepository;
import com.miaotech.order.infra.db.mapper.CommodityMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chen
 * @since 2021-05-14
 */
@Repository
public class CommodityRepositoryImpl extends ServiceImpl<CommodityMapper, Commodity> implements CommodityRepository {

    @Override
    public void decreaseStorage(String commodityCode, Integer count) {
        this.baseMapper.decreaseStorage(commodityCode, count);
    }

    @Override
    public Commodity lockCommodity(String commodityCode) {
        return query().eq("commodity_code", commodityCode).last("for update").one();
    }

    @Override
    public Commodity findCommodity(String commodityCode) {
        return query().eq("commodity_code", commodityCode).one();
    }

}
