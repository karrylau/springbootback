package com.karry.springbootmybatis.controller;

import com.karry.springbootmybatis.pojo.Forecast;
import com.karry.springbootmybatis.service.impl.ForecastPeopleService;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequestMapping("/api")
public class ForecastPeopleController {
    private final ForecastPeopleService service;

    public ForecastPeopleController(ForecastPeopleService service) {
        this.service = service;
    }


    @GetMapping("/forecast/primaryEnrollment")
    public int primaryEnrollment(@RequestBody Forecast param) {
        int targetYear =param.getYear();
        String location = param.getLocation();
        return service.calculatePrimaryEnrollment(targetYear, location);
    }


    @GetMapping("/forecast/juniorEnrollment")
    public int juniorEnrollment(@RequestBody Forecast param) {
        int targetYear =param.getYear();
        String location = param.getLocation();
        return service.calculateJuniorEnrollment(targetYear, location);
    }


    @GetMapping("/forecast/seniorEnrollment")
    public int seniorEnrollment(@RequestBody Forecast param) {
        int targetYear =param.getYear();
        String location = param.getLocation();
        return service.calculateSeniorEnrollment(targetYear, location);
    }


    @GetMapping("/forecast/primaryInSchool")
    public int primaryInSchool(@RequestBody Forecast param) {
        int targetYear =param.getYear();
        String location = param.getLocation();
        return service.calculatePrimaryInSchool(targetYear, location);
    }


    @GetMapping("/forecast/juniorInSchool")
    public int juniorInSchool(@RequestBody Forecast param) {
        int targetYear =param.getYear();
        String location = param.getLocation();
        return service.calculateJuniorInSchool(targetYear, location);
    }


    @GetMapping("/forecast/seniorInSchool")
    public int seniorInSchool(@RequestBody Forecast param) {
        int targetYear =param.getYear();
        String location = param.getLocation();
        return service.calculateSeniorInSchool(targetYear, location);
    }
    // 师生比接口
    @GetMapping("/forecast/shishengbi")
    public Map<Integer, Double> getTeacherStudentRatio(@RequestBody Forecast param) {
        int targetYear =param.getYear();
        String location = param.getLocation();
        String stage = param.getStage();
        return service.calculateTeacherStudentRatio(targetYear, location, stage);
    }
}