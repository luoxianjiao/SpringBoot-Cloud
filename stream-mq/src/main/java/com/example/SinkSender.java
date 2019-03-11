package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

/**
 * @ClassName: SinkSender
 * @Description: 消息生产者应用
 * @Author Comsys-xianjiao.luo
 * @Date 2018/12/13 18:42
 **/
@EnableBinding(value = {Source.class})
public class SinkSender {

    private static Logger logger= LoggerFactory.getLogger(SinkSender.class);
    @Bean
    @InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "2000"))
    public MessageSource<String> timerMessageSource() {
        return () -> new GenericMessage<>("{\"name\":\"lxj\", \"age\":20}");
    }
}
