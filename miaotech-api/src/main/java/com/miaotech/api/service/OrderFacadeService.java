package com.miaotech.api.service;

import com.miaotech.api.command.CreateOrderCmd;
import com.miaotech.api.command.OrderCmd;

public interface OrderFacadeService {

    /**
     * 创建订单
     * @param createOrderCmd
     */
    void createOrder(CreateOrderCmd createOrderCmd);

    /**
     * 完成订单
     * @param orderCmd
     */
    void finishOrder(OrderCmd orderCmd);

}
