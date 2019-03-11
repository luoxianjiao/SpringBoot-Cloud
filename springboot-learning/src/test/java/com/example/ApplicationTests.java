package com.example;

import com.example.async.AsyncTask;
import com.example.dto.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


/**
 * @ClassName: ApplicationTests
 * @Description: TODO
 * @Author Comsys-xianjiao.luo
 * @Date 2019/1/8 14:20
 **/
//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
//    @Autowired
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private AsyncTask asyncTask;

    @Test
    public void testAsyncTask() throws Exception {
        asyncTask.doTaskOne();
        asyncTask.doTaskTwo();
        asyncTask.doTaskThree();
    }
    @Test
    public void test() throws Exception {

        // 保存对象
//        User user = new User("超人", 20);
//        redisTemplate.opsForValue().set(user.getName(), "aa");

//        user = new User("蝙蝠侠", 30);
//        redisTemplate.opsForValue().set(user.getName(), user);
//
//        user = new User("蜘蛛侠", 40);
//        redisTemplate.opsForValue().set(user.getName(), user);

//        Assert.assertEquals(20, redisTemplate.opsForValue().get("超人").getAge().longValue());
//        Assert.assertEquals(30, redisTemplate.opsForValue().get("蝙蝠侠").getAge().longValue());
//        Assert.assertEquals(40, redisTemplate.opsForValue().get("蜘蛛侠").getAge().longValue());
//        System.out.println(redisTemplate.opsForValue().get("超人").getAge().longValue());
//        System.out.println(redisTemplate.opsForValue().get("蝙蝠侠").getAge().longValue());
//        System.out.println(redisTemplate.opsForValue().get("蜘蛛侠").getAge().longValue());
    }


}
