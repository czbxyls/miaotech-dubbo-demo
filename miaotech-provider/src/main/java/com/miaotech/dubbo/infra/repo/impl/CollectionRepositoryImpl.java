package com.miaotech.dubbo.infra.repo.impl;


import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miaotech.dubbo.domain.repo.CollectionRepository;
import com.miaotech.dubbo.domain.entity.Collection;
import com.miaotech.dubbo.infra.repo.mapper.CollectionMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CollectionRepositoryImpl extends ServiceImpl<CollectionMapper, Collection> implements CollectionRepository, IService<Collection> {

}
