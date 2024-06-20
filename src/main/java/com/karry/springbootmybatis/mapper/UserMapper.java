package com.karry.springbootmybatis.mapper;

import com.karry.springbootmybatis.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UserMapper {

    @Select("select * from  \"user\"")
    List<User> list();
//    @Select("select * from \"user\" limit #{start},#{end}")
//    public User findUserById(Integer id);
public User findUserById();

}


