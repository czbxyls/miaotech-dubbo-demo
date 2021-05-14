package com.miaotech.user.common;

import com.miaotech.api.dto.CollectUrlDTO;
import com.miaotech.user.BaseTest;
import com.miaotech.user.infra.mq.producer.CollectSenderService;
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
