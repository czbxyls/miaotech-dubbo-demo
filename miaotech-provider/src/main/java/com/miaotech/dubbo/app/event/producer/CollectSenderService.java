package com.miaotech.dubbo.app.event.producer;

import com.miaotech.dubbo.infra.mq.MQSource;
import com.miaotech.dubbo.infra.mq.producer.BaseSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CollectSenderService extends BaseSenderService {

    @Autowired
    private MQSource source;

    @PostConstruct
    public void init() {
        this.messageChannel = source.collectOutput();
    }


}
