package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class SleuthTrace2Application {

    private static Logger logger= LoggerFactory.getLogger(SleuthTrace2Application.class);

    @RequestMapping(value = "/trace-2",method = RequestMethod.GET)
    public String trace(HttpServletRequest request) throws Exception{
//        Thread.sleep(3000);
        logger.info("call sleuth-trace-2, TraceId={}, SpanId={}", request.getHeader("X-B3-TraceId"), request.getHeader("X-B3-SpanId"));
        return "Trace";
    }

    public static void main(String[] args) {
        SpringApplication.run(SleuthTrace2Application.class, args);
    }

}

