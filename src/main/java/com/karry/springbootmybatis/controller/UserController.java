package com.karry.springbootmybatis.controller;
import  com.karry.springbootmybatis.pojo.User;
import com.karry.springbootmybatis.pojo.result;
import com.karry.springbootmybatis.service.UserService;
import org.apache.ibatis.annotations.Delete;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import com.karry.springbootmybatis.service.UserService;
@Slf4j
@RestController
@RequestMapping("/api/findUserById")
public class UserController {

    @Autowired
    private UserService UserService;

    @GetMapping
    public result list(){
        log.info("查询全部部门数据");
        //调用service查询部门数据
        List<User> deptList =  UserService.list();
        return result.success(deptList);
    }}
//    public User findUserById(Integer id) {
//        System.out.println("ok");
//        return UserService.findUserById(id);
//
//    }
//    public User findUserById() {
//        System.out.println("ok");
//        return UserService.findUserById();
//
//    }
