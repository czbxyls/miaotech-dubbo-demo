package com.miaotech.user.infra.db.impl;


import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miaotech.user.domain.repo.CollectionRepository;
import com.miaotech.user.domain.entity.Collection;
import com.miaotech.user.domain.valueobject.HotCollection;
import com.miaotech.user.infra.db.mapper.CollectionMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CollectionRepositoryImpl extends ServiceImpl<CollectionMapper, Collection> implements CollectionRepository, IService<Collection> {

    public List<HotCollection> getHotCollections(int size) {
        return baseMapper.selectHotCollections(size);
    }

}
