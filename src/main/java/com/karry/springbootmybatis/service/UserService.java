package com.karry.springbootmybatis.service;

import com.karry.springbootmybatis.pojo.User;

import java.util.List;

public interface UserService {
//    public User findUserById(Integer id);
public User findUserById();
//全面查询
List<User> list();
//删除操作

}

