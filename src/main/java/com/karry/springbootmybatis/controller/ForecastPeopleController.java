package com.karry.springbootmybatis.controller;

import com.karry.springbootmybatis.service.ForecastPeopleService;
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
     * @param targetYear 目标年份
     * @param location 目标地区
     * @return 初中在籍人数
     */
    @GetMapping("/forecast/juniorInSchool")
    public int juniorInSchool(@RequestParam int targetYear, @RequestParam String location) {
        return service.calculateJuniorInSchool(targetYear, location);
    }

    /**
     * 高中在籍人数接口
     * @param targetYear 目标年份
     * @param location 目标地区
     * @return 高中在籍人数
     */
    @GetMapping("/forecast/seniorInSchool")
    public int seniorInSchool(@RequestParam int targetYear, @RequestParam String location) {
        return service.calculateSeniorInSchool(targetYear, location);
    }
}