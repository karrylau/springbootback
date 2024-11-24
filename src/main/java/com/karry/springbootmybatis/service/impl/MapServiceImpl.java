package com.karry.springbootmybatis.service.impl;
import com.karry.springbootmybatis.mapper.MapMapper;
import com.karry.springbootmybatis.pojo.SchoolFeature;
import com.karry.springbootmybatis.pojo.feature;
import com.karry.springbootmybatis.pojo.map;
import com.karry.springbootmybatis.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
                        (String) school.get("location"),
                        Double.parseDouble(school.get("longitude").toString()),
                        Double.parseDouble(school.get("latitude").toString()),
                        Double.parseDouble(school.get("weight").toString())
                ))
                .collect(Collectors.toList());

        Map<String, List<SchoolFeature>> dataset = Map.of("dataset1", features);
        return dataset;
    }

    @Override
    public map getMapData() {
        map result = null;
        List<Map<String, Object>> schools = schoolMapper.getAllSchools();
        List<feature> SchoolData = new ArrayList<>();
        int n=0;
        for (Map<String, Object> data : schools) {
            feature t = new feature();
            t.setLongitude((Double) data.get("longitude"));
            t.setLatitude((Double)data.get("latitude"));
            t.setWeight((Double)data.get("weight"));
            t.setName((String)data.get("location"));

            SchoolData.add(t);
        }

        List<Map<String, Object>> coordinate = schoolMapper.getCoordinate();
        List<Map<String, Object>> teachernum = schoolMapper.getTeacherNum();
        List<feature> TeacherData = new ArrayList<>();



//        List<Map<String, Object>> schools = schoolMapper.getAllSchools();
//        List<Map<String, Object>> schools = schoolMapper.getAllSchools();

        result.setSchools(SchoolData);
        return result;
    }
}

// SchoolFeature.java

