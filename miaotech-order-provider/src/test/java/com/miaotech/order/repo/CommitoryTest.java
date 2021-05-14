package com.miaotech.order.repo;

import com.alibaba.fastjson.JSON;
import com.miaotech.order.BaseTest;
import com.miaotech.order.domain.entity.Commodity;
import com.miaotech.order.domain.repo.CommodityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author chen
 * @Date 2021/5/14
 */
public class CommitoryTest extends BaseTest {

    @Autowired
    private CommodityRepository commodityRepository;

    @Test
    public void findTest() {
        Commodity commodity = commodityRepository.findCommodity("test");
        System.out.println(JSON.toJSONString(commodity));
    }

    @Test
    public void lockTest() {
        Commodity commodity = commodityRepository.lockCommodity("test");
        System.out.println(JSON.toJSONString(commodity));
    }

    @Test
    public void decreaseStorageTest() {
        commodityRepository.decreaseStorage("test", 1);
    }
}
