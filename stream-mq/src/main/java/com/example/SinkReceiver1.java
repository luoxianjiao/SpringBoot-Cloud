package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @ClassName: SinkReceiver1
 * @Description: 消费者（接收RabbitMQ消息）
 * @Author Comsys-xianjiao.luo
 * @Date 2018/12/12 16:12
 **/
@EnableBinding(Sink.class)
public class SinkReceiver1 {

    private static Logger logger= LoggerFactory.getLogger(SinkReceiver1.class);

    @StreamListener(Sink.INPUT)
    public void receive(Object payload) {
        logger.info("Received-1："+payload);
    }

}
