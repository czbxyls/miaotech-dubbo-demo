package com.miaotech.order.infra.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miaotech.order.domain.entity.Commodity;
import com.miaotech.order.domain.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chen
 * @since 2021-05-14
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 更新订单状态
     * @param orderNo
     * @param status 更新的状态
     * @param status 之前的状态
     * @return
     */
    int updateStatus(@Param("orderNo") String orderNo,
                     @Param("status") Integer status,
                     @Param("prevStatus") Integer prevStatus);
}
