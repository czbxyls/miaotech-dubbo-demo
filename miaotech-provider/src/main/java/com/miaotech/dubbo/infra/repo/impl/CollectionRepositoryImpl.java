package com.miaotech.dubbo.infra.repo.impl;


import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miaotech.api.dto.CollectionDTO;
import com.miaotech.dubbo.domain.repo.CollectionRepository;
import com.miaotech.dubbo.domain.entity.Collection;
import com.miaotech.dubbo.infra.repo.mapper.CollectionMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CollectionRepositoryImpl extends ServiceImpl<CollectionMapper, Collection> implements CollectionRepository, IService<Collection> {

    public List<Collection> getHotCollections(int size) {
        return baseMapper.selectHotCollections(size);
    }

}
