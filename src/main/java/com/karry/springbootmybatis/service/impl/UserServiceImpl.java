package com.karry.springbootmybatis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.karry.springbootmybatis.pojo.User;
import com.karry.springbootmybatis.mapper.UserMapper;
import com.karry.springbootmybatis.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Override
//    public User findUserById(Integer id) {
//        return userMapper.findUserById(id);
//    }
//}
    public User findUserById() {
        return userMapper.findUserById();
    }
    @Override
    public List<User> list() {
        return userMapper.list();
    }
}
