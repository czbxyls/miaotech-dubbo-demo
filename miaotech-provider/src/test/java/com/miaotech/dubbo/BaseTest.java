package com.miaotech.dubbo;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan({"com.miaotech.common", "com.miaotech.dubbo"})
public class BaseTest {

}
