package com.karry.springbootmybatis.service.impl;
import com.karry.springbootmybatis.mapper.MapMapper;
import com.karry.springbootmybatis.pojo.SchoolFeature;
import com.karry.springbootmybatis.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MapServiceImpl implements MapService {
    @Autowired
    private MapMapper schoolMapper;

    @Override
    public Map<String, List<SchoolFeature>> getSchoolDataset() {
        List<Map<String, Object>> schools = schoolMapper.getAllSchools();
        List<SchoolFeature> features = schools.stream()
                .map(school -> new SchoolFeature(
                        (String) school.get("school"),
                        Double.parseDouble(school.get("longitude").toString()),
                        Double.parseDouble(school.get("latitude").toString()),
                        Double.parseDouble(school.get("weight").toString())
                ))
                .collect(Collectors.toList());

        Map<String, List<SchoolFeature>> dataset = Map.of("dataset1", features);
        return dataset;
    }
}

// SchoolFeature.java

