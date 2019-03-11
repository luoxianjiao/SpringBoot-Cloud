package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@EnableBinding(value = {StreamMqApplicationTests.SinkSender.class})
public class StreamMqApplicationTests {

    //@Autowired导致sinkSender变量报红线（idea的bug，原因是idea的spring插件不能识别出来@autowire）
    // 临时解决：settings -> inspections -> spring -> spring core -> code -> autowiring for bean class，修改成黄色 warning
    @Autowired
    private SinkSender sinkSender;

    //生产消息的单元测试用例
    @Test
    public void sinkSenderTest() {
        sinkSender.output().send(MessageBuilder.withPayload("produce a message xxxxxxx").build());
    }


    public interface SinkSender {
        String OUTPUT = "input";

        @Output(SinkSender.OUTPUT)
        MessageChannel output();
    }



}
