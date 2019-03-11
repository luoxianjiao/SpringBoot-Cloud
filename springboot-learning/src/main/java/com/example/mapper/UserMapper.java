package com.example.mapper;

import com.example.domain.UserEntity;
import com.example.enums.UserSexEnum;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @ClassName: UserMapper
 * @Description: TODO
 * @Author Comsys-xianjiao.luo
 * @Date 2019/1/8 17:16
 **/

//@CacheConfig(cacheNames = "users")
//在接口类上添加了@Mapper，在编译之后会生成相应的接口实现类
//@Mapper
public interface UserMapper {



    @Select("select * from users")
    @Results({ //返回结果的绑定
            @Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")
    })
    List<UserEntity> getAll();

//    @Cacheable(key = "#p0")
    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
            @Result(property = "userSex",  column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")
    })
    UserEntity getById(Long id);

    @Insert("INSERT INTO users(userName,passWord,user_sex) VALUES(#{userName}, #{passWord}, #{userSex})")
    void insert(UserEntity userEntity);

    @Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
    void update(UserEntity userEntity);

    @Delete("delete FROM users WHERE id =#{id}")
    void delete(Long id);





}
