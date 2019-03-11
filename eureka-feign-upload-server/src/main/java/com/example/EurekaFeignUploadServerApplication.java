package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传，服务提供方
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaFeignUploadServerApplication {

    @RestController
    public class UploadController {
        @PostMapping(value = "/fileUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public String fileUpload(@RequestPart(value="file") MultipartFile file) {
            return file.getName();
        }
    }

    public static void main(String[] args) {
//        SpringApplication.run(EurekaFeignUploadServerApplication.class, args);
        new SpringApplicationBuilder(EurekaFeignUploadServerApplication.class).web(true).run(args);
    }

}

