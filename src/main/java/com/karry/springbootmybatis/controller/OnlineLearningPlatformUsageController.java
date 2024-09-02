package com.karry.springbootmybatis.controller;

import com.karry.springbootmybatis.service.impl.OnlineLearningPlatformUsageServicelmpl;
import com.karry.springbootmybatis.pojo.OnlineLearningPlatformUsage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.List;


@RestController
public class OnlineLearningPlatformUsageController {
    private final OnlineLearningPlatformUsageServicelmpl service;

    public OnlineLearningPlatformUsageController(OnlineLearningPlatformUsageServicelmpl service) {
        this.service = service;
    }

    @GetMapping("/platform-usage")
    public List<OnlineLearningPlatformUsage> getAllPlatformUsage() {
        return service.getAllUsageData();
    }

    @GetMapping("/all-data")
    public Map<String, List<Object>> getAllData() {
        return service.getAllData();
    }
}

