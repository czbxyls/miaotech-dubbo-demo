package com.miaotech.order.domain.service;

import com.miaotech.common.MsgException;
import com.miaotech.common.id.IDGeneratorProvider;
import com.miaotech.order.domain.entity.Order;
import com.miaotech.order.domain.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @Author chen
 * @Date 2021/5/14
 */
@Service
public class OrderDomainServiceImpl implements OrderDomainService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private IDGeneratorProvider idGeneratorProvider;

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public int createOrder(Order order) {
        order.setOrderNo(idGeneratorProvider.nextId());
        order.setAddtime(new Date());
        orderRepository.createOrder(order);
        return order.getId();
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public void finishOrder(int userId, String orderNo) {
        Order order = orderRepository.findOrder(orderNo);
        if(order == null) throw MsgException.newMessageException("无效的订单号：" + orderNo);
        if(order.getUserId() != userId)
            throw MsgException.newMessageException("非法操作，该订单号不属于当前用户！");
        int ret = orderRepository.finishOrder(orderNo);
        if(ret <= 0) throw MsgException.newMessageException("更新订单失败，订单状态。orderNo=" + orderNo);
    }
}
