package com.miaotech.dubbo.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Configuration
public class ServerContainer {
    //is it thread safe?
    @PostConstruct
    public void init(){
        log.info("Server initializer begin......");
        System.out.println("hello! I'm initialize something!");
        log.info("Server initializer end......");
    }

    @PreDestroy
    public void destroy(){
        log.info("Shutdown process initiated...");
        System.out.println("hello! I'm release something!");
        log.info("Graceful shutdown is processed successfully");
    }
}
