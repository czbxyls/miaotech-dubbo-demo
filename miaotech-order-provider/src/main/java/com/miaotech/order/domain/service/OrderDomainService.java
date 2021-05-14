package com.miaotech.order.domain.service;

import com.miaotech.order.domain.entity.Order;

/**
 * @Author chen
 * @Date 2021/5/14
 */
public interface OrderDomainService {
    /**
     * 创建订单
     * @param order
     */
    int createOrder(Order order);

    /**
     * 完成订单
     * @param orderNo
     */
    void finishOrder(int userId, String orderNo);
}
