package com.karry.springbootmybatis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.karry.springbootmybatis.pojo.User;
import com.karry.springbootmybatis.mapper.UserMapper;
import com.karry.springbootmybatis.service.UserService;

import java.time.LocalDateTime;
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
    //id查询
    public User findUserById() {
        return userMapper.findUserById();
    }
    @Override
    public List<User> list() {
        return userMapper.list();
    }
    public void delete(User user) {
        userMapper.delete(user);
    }
    //增加新的内容
    public void add(User user) {
//        可以对于时间进行设置
//        user.setCreateTime(LocalDateTime.now());
//        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
    }
}
