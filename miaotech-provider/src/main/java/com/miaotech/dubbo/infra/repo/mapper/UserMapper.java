package com.miaotech.dubbo.infra.repo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miaotech.dubbo.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口：用户表
 * </p>
 *
 * @author billchen
 * @since 2021-04-22
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
