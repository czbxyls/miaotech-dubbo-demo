package com.miaotech.order.infra.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miaotech.order.domain.entity.Commodity;
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
public interface CommodityMapper extends BaseMapper<Commodity> {

    /**
     * 扣减商品库存
     * @Param: commodityCode 商品code  count扣减数量
     * @Return:
     */
    int decreaseStorage(@Param("commodityCode") String commodityCode, @Param("count") Integer count);

}
