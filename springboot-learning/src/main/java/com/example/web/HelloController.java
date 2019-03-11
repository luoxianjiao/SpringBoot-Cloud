package com.example.web;

import com.example.async.AsyncTask;
import com.example.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.Future;

/**
 * @ClassName: HelloController
 * @Description: TODO
 * @Author Comsys-xianjiao.luo
 * @Date 2019/1/7 16:43
 **/
@Controller
public class HelloController {
//    @Autowired
//    private RedisTemplate<String, User> redisTemplate;
    @Autowired
    private AsyncTask asyncTask;
    @RequestMapping("/")
    public String index() {
        User user = new User("超人", 20);
//        redisTemplate.opsForValue().set(user.getName(), user);
        return "index";
    }

    @RequestMapping("/hello")
    public String hello() throws Exception{
        asyncTask.doTaskOne();
        asyncTask.doTaskTwo();
        asyncTask.doTaskThree();
        StringBuffer sb = new StringBuffer();
        sb.append("1");
        sb.toString();
        return "hello";
    }

    @RequestMapping("/testAsyncTask")
    public String testAsyncTask() throws Exception {
        long start = System.currentTimeMillis();

        Future<String> task1 = asyncTask.doTaskOne();
        Future<String> task2 = asyncTask.doTaskTwo();
        Future<String> task3 = asyncTask.doTaskThree();

        while(true) {
            if(task1.isDone() && task2.isDone() && task3.isDone()) {
                // 三个任务都调用完成，退出循环等待
                break;
            }
            Thread.sleep(1000);
        }

        long end = System.currentTimeMillis();

        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
        return "hello";
    }
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
