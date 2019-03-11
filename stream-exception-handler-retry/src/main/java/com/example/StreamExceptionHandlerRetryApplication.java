package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableBinding(StreamExceptionHandlerRetryApplication.TestTopic.class)
@SpringBootApplication
public class StreamExceptionHandlerRetryApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamExceptionHandlerRetryApplication.class, args);
    }

    @RestController
    static class TestController {
        @Autowired
        private TestTopic testTopic;

        /**
         * 消息生产接口
         * @param message
         * @return
         */
        @GetMapping("/sendMessage")
        public String messageWithMQ(@RequestParam String message) {
            testTopic.output().send(MessageBuilder.withPayload(message).build());
            return "ok";
        }
    }

    @Slf4j
    @Component
    static class TestListener {
        int counter = 1;
        @StreamListener(TestTopic.INPUT)
        public void receive(String payload) {
            /*if("hello".equals(payload)) {
                log.info("Received: " + payload + ", " + counter);
            } else {
                throw new RuntimeException("Message consumer failed!");
            }*/
            log.info("Received payload : " + payload + ", " + counter);
            if (counter == 3) {
                counter = 1;
                throw new AmqpRejectAndDontRequeueException("tried 3 times failed, send to dlq!");
            } else {
                counter ++;
                throw new RuntimeException("Message consumer failed!");
            }


        }

        /**
         * 消息消费失败，降级处理逻辑，目前不建议用（官方有bug，需在2.1以后修复）
         * @param message
         */
       /* @ServiceActivator(inputChannel = "test-topic.stream-exception-handler.errors")
        public void error(Message<?> message) {
            log.info("Message consumer failed, call fallback!");
        }*/
    }

    interface TestTopic {

        String OUTPUT = "example-topic-output";
        String INPUT = "example-topic-input";

        @Output(OUTPUT)
        MessageChannel output();

        @Input(INPUT)
        SubscribableChannel input();

    }

}

