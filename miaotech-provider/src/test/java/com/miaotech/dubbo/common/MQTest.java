package com.miaotech.dubbo.common;

import com.miaotech.api.dto.CollectUrlDTO;
import com.miaotech.dubbo.BaseTest;
import com.miaotech.dubbo.app.event.producer.CollectSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class MQTest extends BaseTest {
    @Autowired
    private CollectSenderService collectSenderService;

    @Test
    public void sendTest() throws InterruptedException {
        CollectUrlDTO collectUrlDTO = new CollectUrlDTO();
        collectUrlDTO.setUrl("https://www.baidu.com");

        collectSenderService.sendObject(collectUrlDTO);

        Thread.sleep(1000L);
    }
}
