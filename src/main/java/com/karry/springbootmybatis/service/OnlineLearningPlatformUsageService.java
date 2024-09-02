package com.karry.springbootmybatis.service;

import com.karry.springbootmybatis.pojo.OnlineLearningPlatformUsage;

import java.util.List;
import java.util.Map;

public interface OnlineLearningPlatformUsageService
{
    List<OnlineLearningPlatformUsage> getAllUsageData();

    Map<String, List<Object>> getAllData();
}
