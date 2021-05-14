package com.miaotech.order.repo;

import com.alibaba.fastjson.JSON;
import com.miaotech.order.BaseTest;
import com.miaotech.order.domain.entity.Commodity;
import com.miaotech.order.domain.entity.Order;
import com.miaotech.order.domain.repo.CommodityRepository;
import com.miaotech.order.domain.repo.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author chen
 * @Date 2021/5/14
 */
public class OrderTest extends BaseTest {

    @Autowired
    private OrderRepository orderRepository;

    //@Test
    public void findTest() {
        Order order = orderRepository.findOrder("test");
        System.out.println(JSON.toJSONString(order));
    }

    @Test
    public void finishOrderTest() {
        orderRepository.finishOrder("test");
    }

}
