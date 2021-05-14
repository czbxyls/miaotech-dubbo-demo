package com.miaotech.user.infra.mq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 定义输出流
 */
public interface MQSource {

    String NOTIFY_OUTPUT = "notifyOutput";
    String COLLECT_OUTPUT = "collectOutput";

    @Output(NOTIFY_OUTPUT)
    MessageChannel notifyOutput();

    @Output(COLLECT_OUTPUT)
    MessageChannel collectOutput();
}
