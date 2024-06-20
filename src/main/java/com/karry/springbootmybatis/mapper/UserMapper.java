package com.karry.springbootmybatis.mapper;

import com.karry.springbootmybatis.pojo.GiniCoefficient;
import com.karry.springbootmybatis.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UserMapper {
//全部查询
    @Select("select * from  \"user\"")
    List<User> list();
//    @Select("select * from \"user\" limit #{start},#{end}")
//    public User findUserById(Integer id);
    //id查询
public User findUserById();

//根据id删除
    @Delete("delete from \"user\" where id = #{id}")
    void delete(User user);
    @Insert("insert into \"user\"(id, name, age) values(#{id}, #{name}, #{age})")
    void insert(User user);

}


