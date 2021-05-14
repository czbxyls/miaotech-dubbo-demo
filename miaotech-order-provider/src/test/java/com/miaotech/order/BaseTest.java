package com.miaotech.order;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest(classes = MiaoTechOrderProvider.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan({"com.miaotech.common", "com.miaotech.order"})
public class BaseTest {

}
