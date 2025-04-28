package com.karry.springbootmybatis.controller;

import com.karry.springbootmybatis.pojo.homenum;
import com.karry.springbootmybatis.pojo.humanRes;
import com.karry.springbootmybatis.pojo.numdong;
import com.karry.springbootmybatis.service.HumanRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class HumanRController {

    @Autowired
    private HumanRService humanRService;

    @GetMapping("/all")
    public humanRes getAllGeniService() {
        return humanRService.getAll();
    }

    @GetMapping("/home")    //地图两边的五个数字
    public homenum gethomenum(@RequestParam("year") Integer year) {
        System.out.println(year);
        return humanRService.getHomenum(year);
    }

//    @GetMapping("/getAllH")
//    public humanRes getAllH() {
//        System.out.println("1");
//        return humanRService.getAll();
//    }
    @PostMapping("/filter")      //按年份，学段，地区筛选
    public humanRes getFilteredData(@RequestParam Integer year, @RequestParam String stage, @RequestParam String location) {
        return humanRService.getFilteredData(year, stage, location);
    }

    @GetMapping("/snumdata")  //学生人数图表连接
    public numdong getsnum(){
        return humanRService.getSnum();
    }

    @GetMapping("/teacherdata")  //教师人数图表连接
    public numdong gettnum(){
        return humanRService.getTnum();
    }
}