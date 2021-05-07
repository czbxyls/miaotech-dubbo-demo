package com.miaotech.common;

import com.miaotech.common.id.IDGeneratorProvider;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = IDGeneratorProvider.class)
@TestPropertySource("classpath:application.properties")
//@EnableConfigurationProperties(value = ServerConfig.class)
public class IDProviderTest extends BaseTest {

    @Autowired
    private IDGeneratorProvider idGeneratorProvider;

    @Tag("fast")
    @Test
    void idTest() {
        for(int i=0;i<100;i++){
            String id = idGeneratorProvider.nextId();
            System.out.println(id);
        }
    }
}
