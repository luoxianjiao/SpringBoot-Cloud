package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: DcController
 * @Description: 服务提供者
 * @Author Comsys-xianjiao.luo
 * @Date 2018/10/31 17:10
 **/
@RestController
public class DcController {

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/dc")
    public String dc() {
        try {
            Thread.sleep(500L);//add sleep
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String services = "Services: "+discoveryClient.getServices();
        System.out.println(services+"bbb");
        return services;
    }
}
