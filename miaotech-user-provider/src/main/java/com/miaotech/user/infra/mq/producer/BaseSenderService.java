package com.miaotech.user.infra.mq.producer;

import com.miaotech.user.infra.mq.MQConst;
import org.apache.commons.lang.StringUtils;
import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;

public class BaseSenderService {

    protected MessageChannel messageChannel;

    public void send(String msg) {
        messageChannel.send(MessageBuilder.withPayload(msg).build());
    }

//    /**
//     * 发送一个消息
//     * @param msg 消息内容
//     * @param tag 消息标签，用来进一步区分某个Topic下的消息分类，消息从生产者发出即带上的属性
//     * @param <T>
//     */
//    public <T> void sendWithTags(T msg, String tag) {
//        Message message = MessageBuilder.createMessage(msg,
//                new MessageHeaders(Stream.of(tag).collect(Collectors.toMap(str -> MessageConst.PROPERTY_TAGS,
//                        String::toString))));
//        messageChannel.send(message);
//    }


    /**
     * 发送一个消息
     * @param msg 消息内容
     * @param <T>
     */
    public <T> void sendObject(T msg) {
        sendObject(null, msg);
    }

    /**
     * 发送一个消息
     * @param msgType 消息类型
     * @param msg 消息内容
     * @param <T>
     */
    public <T> void sendObject(String msgType, T msg) {
        msgType = StringUtils.isEmpty(msgType) ? msg.getClass().getSimpleName() : msgType;
        Message message = MessageBuilder.withPayload(msg)
                .setHeader(MessageConst.PROPERTY_TAGS, msgType)
                .setHeader(MQConst.PROPERTIES_MSG_TYPE, msgType)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build();
        messageChannel.send(message);
    }

}
