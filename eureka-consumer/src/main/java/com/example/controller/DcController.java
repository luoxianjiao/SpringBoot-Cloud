package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: DcController
 * @Description: TODO
 * @Author Comsys-xianjiao.luo
 * @Date 2018/10/31 18:24
 **/
@RestController
public class DcController {

    @Autowired
    LoadBalancerClient loadBalancerClient;//负载均衡器客户端接口
    @Autowired
    RestTemplate restTemplate;
    @GetMapping("/consumer")
    public String dc() {
        //choose函数来负载均衡的选出一个eureka-client的服务实例
        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client");
        //构造服务请求rest请求链接
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/dc";
        System.out.println(url);
        //利用RestTemplate对象实现对服务提供者接口的调用
        return restTemplate.getForObject(url, String.class);
    }
}
