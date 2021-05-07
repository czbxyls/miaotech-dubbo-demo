package com.miaotech.common.id;

import com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class IDGeneratorProvider {

    @Value("${server.id.dataCenterId:1}")
    private Integer dataCenterId;

    @Value("${server.id.workId:1}")
    private Integer workId;

    @Value("${server.id.workPrefix:NO}")
    private String workPrefix;

    @Autowired
    private SnowFlakeGenerator snowFlakeGenerator;

    @Bean
    @ConditionalOnMissingBean(KeyResolver.class)
    public SnowFlakeGenerator snowFlakeGenerator() {
        log.info("dataCenterId:{}, workId: {}, workPrefix:{}", dataCenterId,workId,workPrefix);
        return new SnowFlakeGenerator(workId, dataCenterId);
    }

    public String nextId() {
        return workPrefix + "_" + snowFlakeGenerator.nextId();
    }

}
