package com.miaotech.user;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest(classes = MiaoTechUserProvider.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan({"com.miaotech.common", "com.miaotech.user"})
public class BaseTest {

}
