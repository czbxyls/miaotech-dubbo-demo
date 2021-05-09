package com.miaotech.dubbo.infra.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 定义输出流
 */
public interface MQSink {

    String NOTIFY_INPUT = "notifyInput";
    String COLLECT_INPUT = "collectInput";

    @Input(NOTIFY_INPUT)
    SubscribableChannel notifyInput();

    @Input(COLLECT_INPUT)
    SubscribableChannel collectInput();
}
