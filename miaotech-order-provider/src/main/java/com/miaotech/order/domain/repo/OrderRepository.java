package com.miaotech.order.domain.repo;

import com.baomidou.mybatisplus.extension.service.IService;
import com.miaotech.order.domain.entity.Order;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chen
 * @since 2021-05-14
 */
public interface OrderRepository{

    /**
     * 创建订单
     * @param order
     */
    void createOrder(Order order);


    /**
     * 完成订单
     * @param orderNo
     */
    int finishOrder(String orderNo);

    /**
     * 根据订单号获取订单信息
     * @param orderNo
     * @return
     */
    Order findOrder(String orderNo);
}
