package com.karry.springbootmybatis.controller;

import com.karry.springbootmybatis.pojo.result;
import com.karry.springbootmybatis.pojo.User;
import com.karry.springbootmybatis.service.UserService;
import com.karry.springbootmybatis.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import cn.hutool.core.util.StrUtil;

@RestController
public class admin {
    @Resource
    UserServiceImpl userService;

    @GetMapping("/")
    public result hello() {
        return result.success("success");
    }

    @PostMapping("api/login")
    public result login(@RequestBody User user) {
//        if (user.getName() == null || user.getPassword().isEmpty()) {
//            return result.error("数据输入不合法");
//        }
        if (StrUtil.isBlank(user.getName()) || StrUtil.isBlank(user.getPassword())) {
            return result.error("数据输入不合法");
        }
        user= userService.login(user);//传递出来的是dbuser
        return result.success(user);
    }
    @PostMapping("api/register")
    public result register(@RequestBody User user) {
//        if (user.getName() == null || user.getPassword().isEmpty()) {
//            return result.error("数据输入不合法");
//        }
        if (StrUtil.isBlank(user.getName()) || StrUtil.isBlank(user.getPassword())) {
            return result.error("数据输入不合法");
        }
        //校验用户名和密码长度
        if (user.getName().length() < 2 || user.getName().length() > 10) {
            return result.error("用户名长度应在2-10之间");
        }
        user= userService.register(user);//传递出来的是dbuser
        return result.success(user);
    }
}
