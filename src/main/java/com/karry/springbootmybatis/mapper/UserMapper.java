package com.karry.springbootmybatis.mapper;

import com.karry.springbootmybatis.pojo.GiniCoefficient;
import com.karry.springbootmybatis.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UserMapper {
@Select("select * from \"user\" where username=#{username} order by id desc ")
   User selectUser(String username);

    @Select("select * from  \"user\"")
    List<User> list();
@Insert("insert into \"user\"(username,password) values(#{username},#{password})")
    void insert(User user);
//    @Select("select * from \"user\" limit #{start},#{end}")
//    public User findUserById(Integer id);
//public User findUserById();


}


