package com.miaotech.dubbo.infra.mq;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;

//配置文件读取是否启用此配置
@EnableBinding({ MQSource.class, MQSink.class })
public class MQStreamConfig {
}

