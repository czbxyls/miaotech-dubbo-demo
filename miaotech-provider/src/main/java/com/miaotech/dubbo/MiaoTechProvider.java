package com.miaotech.dubbo;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PreDestroy;

@SpringBootApplication
@EnableDubbo(scanBasePackages = "com.miaotech.dubbo.facade")
public class MiaoTechProvider {

    public static void main(String[] args) {
        SpringApplication.run(MiaoTechProvider.class, args);
    }
}