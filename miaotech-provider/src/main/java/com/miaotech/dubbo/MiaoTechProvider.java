package com.miaotech.dubbo;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;

@SpringBootApplication
@EnableDubbo(scanBasePackages = "com.miaotech.dubbo.facade")
@ComponentScan({"com.miaotech.common", "com.miaotech.dubbo"})
public class MiaoTechProvider {

    public static void main(String[] args) {
        SpringApplication.run(MiaoTechProvider.class, args);
    }
}