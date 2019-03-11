package com.example.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TestController
 * @Description: TODO
 * @Author Comsys-xianjiao.luo
 * @Date 2018/12/27 11:15
 **/

@RestController
@RefreshScope
public class TestController {

    @Value("${from}")
    private String from;

    @RequestMapping("/from")
    public String from() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
    public String getFrom() {
        return from;
    }
}
