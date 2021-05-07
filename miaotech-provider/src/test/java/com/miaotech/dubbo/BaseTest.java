package com.miaotech.dubbo;


import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

@SpringBootTest(classes = MiaoTechProvider.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan({"com.miaotech.common", "com.miaotech.dubbo"})
public class BaseTest {

}
