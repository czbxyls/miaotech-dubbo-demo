package com.miaotech.dubbo.infra.mq.producer;

import com.miaotech.dubbo.infra.mq.MQSource;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class NotifySenderService extends BaseSenderService {

    @Autowired
    private MQSource source;

    @PostConstruct
    public void init() {
        this.messageChannel = source.notifyOutput();
    }


}
