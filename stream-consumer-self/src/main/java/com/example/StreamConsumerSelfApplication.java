package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(TestTopic.class)
@SpringBootApplication
public class StreamConsumerSelfApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamConsumerSelfApplication.class, args);
    }

}

