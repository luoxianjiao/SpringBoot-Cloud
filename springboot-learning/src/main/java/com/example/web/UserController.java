package com.example.web;

import com.example.domain.UserEntity;
import com.example.dto.User;
import com.example.mapper.UserMapper;
import com.example.mapper.user.UserTestMapper;
import com.example.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.cache.CacheManager;
import java.util.List;


/**
 *springboot对Xml格式数据的消息转换实现只需要加入jackson-dataformat-xml依赖，
 * Spring Boot就会自动引入MappingJackson2XmlHttpMessageConverter的实现
 */
//@Controller
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserTestMapper userTestMapper;
    @Autowired
    private UserService userService;
//    @Autowired
//    private CacheManager cacheManager;
    //测试xml数据格式的请求/响应
    @PostMapping(value = "/user",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public User createUser(@RequestBody User user) {
        user.setName(user.getName() + "Hello");
        user.setAge(user.getAge() + 100);
        return user;
    }

    @RequestMapping(value = "/getUsers",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserEntity> getUsers() {
        List<UserEntity> userEntityList = userMapper.getAll();
        return userEntityList;
    }

    @RequestMapping(value = "/getUser",produces = MediaType.APPLICATION_JSON_VALUE)
    public UserEntity getUser(Long id) {
        UserEntity user=userMapper.getById(id);
        System.out.println("第一次查询：" + user.getUserName());
        UserEntity user1=userMapper.getById(id);
        System.out.println("第二次查询：" + user1.getUserName());
        return user;
    }

    @RequestMapping("/add")
    public void save(UserEntity user) {
        userMapper.insert(user);
    }

    @RequestMapping(value="update")
    public void update(UserEntity user) {
        userMapper.update(user);
    }

    @RequestMapping(value="/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        userMapper.delete(id);
    }

    @RequestMapping("/testTransaction")
    public void testTransaction() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("testTransaction");
        userService.insertUser(userEntity);
    }

}
