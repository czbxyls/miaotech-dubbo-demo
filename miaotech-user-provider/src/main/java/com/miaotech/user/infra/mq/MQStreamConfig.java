package com.miaotech.user.infra.mq;

import org.springframework.cloud.stream.annotation.EnableBinding;

//配置文件读取是否启用此配置
@EnableBinding({ MQSource.class, MQSink.class })
public class MQStreamConfig {
}

