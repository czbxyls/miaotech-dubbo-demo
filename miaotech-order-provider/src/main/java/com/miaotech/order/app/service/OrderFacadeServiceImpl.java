package com.miaotech.order.app.service;

import com.miaotech.api.command.AccountCmd;
import com.miaotech.api.command.CommodityCmd;
import com.miaotech.api.command.CreateOrderCmd;
import com.miaotech.api.command.OrderCmd;
import com.miaotech.api.service.AccountFacadeService;
import com.miaotech.api.service.OrderFacadeService;
import com.miaotech.common.converter.GeneralConvertor;
import com.miaotech.order.domain.entity.Order;
import com.miaotech.order.domain.repo.OrderRepository;
import com.miaotech.order.domain.service.CommodityDomainService;
import com.miaotech.order.domain.service.OrderDomainService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author chen
 * @Date 2021/5/14
 */
@Slf4j
@DubboService(timeout = 3000)
public class OrderFacadeServiceImpl implements OrderFacadeService {
    @Autowired
    private GeneralConvertor generalConvertor;

    @Autowired
    private OrderDomainService orderDomainService;

    @Autowired
    private CommodityDomainService commodityDomainService;

    @DubboReference
    private AccountFacadeService accountFacadeService;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public void createOrder(CreateOrderCmd createOrderCmd) {
        Order order = generalConvertor.convertor(createOrderCmd, Order.class);
        orderDomainService.createOrder(order);
        CommodityCmd commodityCmd = new CommodityCmd(order.getCommodityCode(), order.getCount());
        commodityDomainService.decreaseStorage(commodityCmd);
    }

    @Override
    @GlobalTransactional(timeoutMills = 300000, name = "dubbo-order-seata")
    public void finishOrder(OrderCmd orderCmd) {
        orderDomainService.finishOrder(orderCmd.getUserId(), orderCmd.getOrderNo());

        Order order = orderRepository.findOrder(orderCmd.getOrderNo());
        AccountCmd accountCmd = new AccountCmd(order.getUserId(), order.getMoney());
        accountFacadeService.decreaseAccount(accountCmd);
    }
}
