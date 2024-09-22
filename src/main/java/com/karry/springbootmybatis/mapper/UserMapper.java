package com.karry.springbootmybatis.mapper;

import com.karry.springbootmybatis.pojo.GiniCoefficient;
import com.karry.springbootmybatis.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM \"user\" WHERE name = #{username} ORDER BY id DESC")
    User selectUser(String username);

    @Select("SELECT * FROM \"user\"")
    List<User> list();

    @Insert("INSERT INTO \"user\"(\"name\", \"password\") VALUES(#{name}, #{password})")
      void insert(User user);

    // 保留注释掉的方法供参考
    // @Select("SELECT * FROM \"user\" LIMIT #{start},#{end}")
    // public User findUserById(Integer start, Integer end);
    // public User findUserById();
}