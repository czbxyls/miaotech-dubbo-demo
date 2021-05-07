package com.miaotech.dubbo.infra.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miaotech.dubbo.domain.entity.Collection;
import com.miaotech.dubbo.domain.valueobject.HotCollection;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CollectionMapper extends BaseMapper<Collection> {
    @Select("SELECT url, sum(view_count) viewCount FROM t_collection GROUP BY url ORDER BY viewCount DESC LIMIT #{size}")
    List<HotCollection> selectHotCollections(@Param("size") int size);
}