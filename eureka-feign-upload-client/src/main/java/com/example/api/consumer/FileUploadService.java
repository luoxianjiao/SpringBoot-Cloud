package com.example.api.consumer;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;


/**
 * 文件上传，服务消费方（使用Feign客户端）
 */
@FeignClient(value = "eureka-feign-upload-server", configuration = FileUploadService.MultipartConfig.class)
public interface FileUploadService {

    @PostMapping(value = "fileUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String fileUpload(@RequestPart(value = "file")MultipartFile file);

    @Configuration
    class MultipartConfig {
        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder();
        }
    }
}
