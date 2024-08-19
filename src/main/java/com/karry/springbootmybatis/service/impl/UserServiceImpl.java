package com.karry.springbootmybatis.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.karry.springbootmybatis.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.karry.springbootmybatis.mapper.UserMapper;
import com.karry.springbootmybatis.pojo.User;
import com.karry.springbootmybatis.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User findUserById() {
        return null;
    }

    @Override
    public List<User> list() {
        return userMapper.list();
    }

    // 验证用户账户是否合法
    public User login(User user) {
        // 根据用户名查询数据库的用户信息
        User dbuser = userMapper.selectUser(user.getName());
        if (dbuser == null) {
            // 抛出一个自定义的异常
            throw new ServiceException("用户名或密码错误");
        }
        // 判断密码是否正确
        if (!dbuser.getPassword().equals(user.getPassword())) {
            throw new ServiceException("用户名或密码错误");
        }
        return dbuser;
    }

    public User register(User user) {
        User dbuser = userMapper.selectUser(user.getName());
        if (dbuser != null) {
            throw new ServiceException("用户名已存在");
        }
//        user.setName("honey-"+ RandomUtil.randomNumbers(4));
        user.setName(user.getName());
        userMapper.insert(user);
        return dbuser;
    }
}