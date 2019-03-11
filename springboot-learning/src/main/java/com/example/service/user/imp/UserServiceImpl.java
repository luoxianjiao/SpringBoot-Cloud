package com.example.service.user.imp;

import com.example.domain.UserEntity;
import com.example.mapper.UserMapper;
import com.example.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: UserServiceImpl
 * @Description: TODO
 * @Author Comsys-xianjiao.luo
 * @Date 2019/1/9 18:06
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public Long insertUser(UserEntity userEntity) {
        userMapper.insert(userEntity);
//        throw new RuntimeException();
        return 1l;
    }
}
