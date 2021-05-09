package com.miaotech.dubbo.infra.mq.producer;

import com.miaotech.dubbo.infra.mq.MQSource;
import org.springframework.beans.factory.annotation.Autowired;
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
