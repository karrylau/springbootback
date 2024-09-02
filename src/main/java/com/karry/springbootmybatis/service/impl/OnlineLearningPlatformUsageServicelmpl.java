package com.karry.springbootmybatis.service.impl;

import com.karry.springbootmybatis.mapper.OnlineLearningPlatformUsageMapper;
import com.karry.springbootmybatis.pojo.OnlineLearningPlatformUsage;
import com.karry.springbootmybatis.service.OnlineLearningPlatformUsageService;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class OnlineLearningPlatformUsageServicelmpl implements OnlineLearningPlatformUsageService {
    private final OnlineLearningPlatformUsageMapper mapper;

    public OnlineLearningPlatformUsageServicelmpl(OnlineLearningPlatformUsageMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<OnlineLearningPlatformUsage> getAllUsageData() {
        return mapper.findAll();
    }

    @Override
    public Map<String, List<Object>> getAllData() {
        List<OnlineLearningPlatformUsage> usages = getAllUsageData();
        List<Object> schools = new ArrayList<>();
        List<Object> plats = new ArrayList<>();
        List<Object> rsnums = new ArrayList<>();
        List<Object> rtnums = new ArrayList<>();
        List<Object> sources = new ArrayList<>();
        List<Object> sdownloads = new ArrayList<>();

        for (OnlineLearningPlatformUsage usage : usages) {
            schools.add(usage.getSchool());
            plats.add(usage.getPlat());
            rsnums.add(usage.getRsnum());
            rtnums.add(usage.getRtnum());
            sources.add(usage.getSource());
            sdownloads.add(usage.getSdownload());
        }

        Map<String, List<Object>> result = new LinkedHashMap<>();
        result.put("school", schools);
        result.put("plat", plats);
        result.put("rsnum", rsnums);
        result.put("rtnum", rtnums);
        result.put("source", sources);
        result.put("sdownload", sdownloads);

        return result;
    }

}
