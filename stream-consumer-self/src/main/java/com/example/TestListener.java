package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: TestListener
 * @Description: 监听队列消息
 * @Author Comsys-xianjiao.luo
 * @Date 2019/1/2 11:15
 **/
@Slf4j
@Component
public class TestListener {

    @StreamListener(TestTopic.INPUT)
    public void receive(String payload) {
        log.info("Received: " + payload);
        throw new RuntimeException("BOOM!");
    }
}
