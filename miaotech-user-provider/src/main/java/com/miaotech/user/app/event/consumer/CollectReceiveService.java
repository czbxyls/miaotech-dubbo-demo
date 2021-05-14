package com.miaotech.user.app.event.consumer;

import com.alibaba.fastjson.JSON;
import com.miaotech.api.dto.CollectUrlDTO;
import com.miaotech.api.dto.CollectionDTO;
import com.miaotech.user.infra.mq.MQConst;
import com.miaotech.user.infra.mq.MQSink;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class CollectReceiveService {

    @StreamListener(value=MQSink.COLLECT_INPUT,
            condition = "headers['" + MQConst.PROPERTIES_MSG_TYPE + "']=='CollectUrlDTO'")
    public void receiveCollectLog(@Payload CollectUrlDTO collectUrlDTO) {
        System.out.println("collectInput receive: " + JSON.toJSONString(collectUrlDTO));
    }

    @StreamListener(value=MQSink.COLLECT_INPUT,
            condition = "headers['" + MQConst.PROPERTIES_MSG_TYPE + "']=='CollectionDTO'")
    public void receiveCollectLog2(@Payload CollectionDTO CollectionDTO) {
        System.out.println("collectInput receive: " + JSON.toJSONString(CollectionDTO));
    }
}
