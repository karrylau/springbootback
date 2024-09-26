package com.karry.springbootmybatis.service;

import com.karry.springbootmybatis.pojo.User;

import java.util.List;

public interface UserService {
//    public User findUserById(Integer id);
public User findUserById();
List<User> list();
}
