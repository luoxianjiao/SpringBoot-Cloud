package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: DcController
 * @Description: Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端负载均衡的工具
 * 它是一个基于HTTP和TCP的客户端负载均衡器。它可以通过在客户端中配置ribbonServerList来设置服务端列表去轮询访问以达到均衡负载的作用
 * @Author Comsys-xianjiao.luo
 * @Date 2018/11/01 18:24
 **/
@RestController
public class DcController {

    @Autowired
    LoadBalancerClient loadBalancerClient;//负载均衡器客户端接口
    @Autowired
    RestTemplate restTemplate;
    @GetMapping("/consumer")
    public String dc() {
        //利用RestTemplate对象实现对服务提供者接口的调用
        //Spring Cloud Ribbon有一个拦截器(EurekaConsumerRibbonApplication类中使用@LoadBalanced注解)，它能够在这里进行实际调用的时候，自动的去选取服务实例，
        // 并将实际要请求的IP地址和端口替换这里的服务名，从而完成服务接口的调用
        return restTemplate.getForObject("http://eureka-client/dc", String.class);
    }
}
