package com.example;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableSwagger2Doc
@EnableDiscoveryClient
@SpringBootApplication
public class SwaggerServiceAApplication {

    public static void main(String[] args) {

        SpringApplication.run(SwaggerServiceAApplication.class, args);
    }

    @RestController
    class AaaController {
        @Autowired
        DiscoveryClient discoveryClient;

        @GetMapping("/service-a")
        public String dc(String str) {
            String services = "Services：" + discoveryClient.getServices();
            System.out.println(services);
            return services;
        }

    }

}

