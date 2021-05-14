package com.miaotech.order;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDubbo
@ComponentScan({"com.miaotech.common", "com.miaotech.order"})
public class MiaoTechOrderProvider {
    public static void main(String[] args) {
        SpringApplication.run(MiaoTechOrderProvider.class, args);
    }
}