package com.example.controller;

import com.example.feignclient.DcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: DcController
 * @Description: 通过Spring Cloud Feign来实现服务调用的方式更加简单了，
 * 通过@FeignClient定义的接口来统一的生命我们需要依赖的微服务接口。
 * 而在具体使用的时候就跟调用本地方法一点的进行调用即可。
 * 由于Feign是基于Ribbon实现的，所以它自带了客户端负载均衡功能，也可以通过Ribbon的IRule进行策略扩展。
 * 另外，Feign还整合的Hystrix来实现服务的容错保护，在Dalston版本中，Feign的Hystrix默认是关闭的。
 * 待后文介绍Hystrix入门之后，再结合介绍Feign中的Hystrix以及配置方式
 * @Author Comsys-xianjiao.luo
 * @Date 2018/11/1 15:03
 **/
@RestController
public class DcController {

    @Autowired
    DcClient dcClient;

    @GetMapping("/consumer")
    public String dc() {
        return dcClient.consumer();
    }
}
