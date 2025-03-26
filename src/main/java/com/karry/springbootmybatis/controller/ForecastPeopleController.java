package com.karry.springbootmybatis.controller;

import com.karry.springbootmybatis.service.impl.ForecastPeopleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForecastPeopleController {
    private final ForecastPeopleService service;

    public ForecastPeopleController(ForecastPeopleService service) {
        this.service = service;
    }

    /**
     * 小学入学人数接口
     * @param targetYear 目标年份
     * @param location 目标地区
     * @return 小学入学人数
     */
    @GetMapping("/forecast/primaryEnrollment")
    public int primaryEnrollment(@RequestParam int targetYear, @RequestParam String location) {
        return service.calculatePrimaryEnrollment(targetYear, location);
    }

    /**
     * 初中入学人数接口
     * @param targetYear 目标年份
     * @param location 目标地区
     * @return 初中入学人数
     */
    @GetMapping("/forecast/juniorEnrollment")
    public int juniorEnrollment(@RequestParam int targetYear, @RequestParam String location) {
        return service.calculateJuniorEnrollment(targetYear, location);
    }

    /**
     * 高中入学人数接口
     * @param targetYear 目标年份
     * @param location 目标地区
     * @return 高中入学人数
     */
    @GetMapping("/forecast/seniorEnrollment")
    public int seniorEnrollment(@RequestParam int targetYear, @RequestParam String location) {
        return service.calculateSeniorEnrollment(targetYear, location);
    }

    /**
     * 小学在籍人数接口
     * @param targetYear 目标年份
     * @param location 目标地区
     * @return 小学在籍人数
     */
    @GetMapping("/forecast/primaryInSchool")
    public int primaryInSchool(@RequestParam int targetYear, @RequestParam String location) {
        return service.calculatePrimaryInSchool(targetYear, location);
    }

    /**
     * 初中在籍人数接口
     * 该接口用于获取特定年份和地区的初中在籍人数
     * 主要作用是通过HTTP GET请求提供一个简单的接口，用于获取统计信息
     *
     * @param targetYear 目标年份，指定需要查询的年份
     * @param location 目标地区，指定需要查询的地区
     * @return 初中在籍人数，返回计算得到的初中在籍人数
     */
    @GetMapping("/forecast/juniorInSchool")
    public int juniorInSchool(@RequestParam int targetYear, @RequestParam String location) {
        return service.calculateJuniorInSchool(targetYear, location);
    }

    /**
     * 高中在籍人数接口
     * 该接口用于获取特定年份和地区内的高中在籍学生人数
     * 主要作用是通过目标年份和目标地区参数，从服务层获取高中在籍人数数据
     * 适用于教育统计、教育资源分配等场景
     *
     * @param targetYear 目标年份，用于指定需要查询的年份
     * @param location 目标地区，用于指定需要查询的地区
     * @return 高中在籍人数，返回指定年份和地区的高中在籍学生人数
     */
    @GetMapping("/forecast/seniorInSchool")
    public int seniorInSchool(@RequestParam int targetYear, @RequestParam String location) {
        return service.calculateSeniorInSchool(targetYear, location);
    }
}