package com.miaotech.dubbo.infra.mq;

import lombok.Data;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;

@EnableBinding({ MQSource.class, MQSink.class })
public class MQStreamConfig {
}

