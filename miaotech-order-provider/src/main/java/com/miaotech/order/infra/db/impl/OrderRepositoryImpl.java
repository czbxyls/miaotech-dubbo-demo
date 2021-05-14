package com.miaotech.order.infra.db.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miaotech.order.domain.entity.Order;
import com.miaotech.order.domain.repo.OrderRepository;
import com.miaotech.order.infra.db.mapper.OrderMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chen
 * @since 2021-05-14
 */
@Repository
public class OrderRepositoryImpl extends ServiceImpl<OrderMapper, Order> implements OrderRepository {

    @Override
    public void createOrder(Order order) {
        this.baseMapper.insert(order);
    }

    @Override
    public int finishOrder(String orderNo) {
        return this.baseMapper.updateStatus(orderNo, 1, 0);
    }

    @Override
    public Order findOrder(String orderNo) {
        return query().eq("order_no", orderNo).one();
    }
}
