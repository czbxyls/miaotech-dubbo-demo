package com.miaotech.order.domain.service;

import com.miaotech.api.command.CommodityCmd;
import com.miaotech.common.MsgException;
import com.miaotech.order.domain.entity.Commodity;
import com.miaotech.order.domain.repo.CommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author chen
 * @Date 2021/5/14
 */
@Service
public class CommodityDomainServiceImpl implements CommodityDomainService{

    @Autowired
    private CommodityRepository commodityRepository;

    @Override
    public void decreaseStorage(CommodityCmd commodityCmd) {
        Commodity commodity = commodityRepository.lockCommodity(commodityCmd.getCommodityCode());
        if(commodity == null) {
            throw MsgException.newMessageException("无效的商品code： " + commodityCmd.getCommodityCode());
        }
        if(commodity.getCount() - commodityCmd.getOpCount() <0) {
            throw MsgException.newMessageException("商品库存不足");
        }
        commodityRepository.decreaseStorage(commodityCmd.getCommodityCode(), commodityCmd.getOpCount());
    }
}
