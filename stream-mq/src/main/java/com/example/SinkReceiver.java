package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @ClassName: SinkReceiver
 * @Description: 消费者（接收RabbitMQ消息）
 * @Author Comsys-xianjiao.luo
 * @Date 2018/12/12 16:12
 **/
@EnableBinding(Sink.class)
public class SinkReceiver {

    private static Logger logger= LoggerFactory.getLogger(SinkReceiver.class);

    @StreamListener(Sink.INPUT)
    public void receive(Object payload) {
        logger.info("Received："+payload);
    }

}
