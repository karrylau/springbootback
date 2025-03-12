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
        List<Map<String, Object>> teachernum = schoolMapper.getNum();
        List<Map<String, Object>> fixed = schoolMapper.getFixed();
        List<feature> TeacherData = new ArrayList<>();
        List<feature> StudentData = new ArrayList<>();
        List<feature> FixedAssets = new ArrayList<>();
        for (int i = 0; i < coordinate.size(); i++) {
            feature t = new feature();//分别获取两库数据
            feature k = new feature();
            feature p = new feature();

            //weight赋值
            t.setWeight(Double.valueOf((Integer) teachernum.get(i).get("Tnum")));
            k.setWeight(Double.valueOf((Integer) teachernum.get(i).get("Snum")));
            p.setWeight((Double) fixed.get(i).get("fixedassets"));


            //坐标赋值
            t.setLongitude((Double)coordinate.get(i).get("longitude"));
            t.setLatitude((Double) coordinate.get(i).get("latitude"));
            t.setName((String) coordinate.get(i).get("location"));

            k.setLongitude((Double)coordinate.get(i).get("longitude"));
            k.setLatitude((Double) coordinate.get(i).get("latitude"));
            k.setName((String) coordinate.get(i).get("location"));

            p.setLongitude((Double)coordinate.get(i).get("longitude"));
            p.setLatitude((Double) coordinate.get(i).get("latitude"));
            p.setName((String) coordinate.get(i).get("location"));
            //暂时以EduCost为例，后续换成占位符，接收数据加入费用类型即可
            TeacherData.add(t);
            StudentData.add(k);
            FixedAssets.add(p);
        }


        map result = new map();
        result.setSchools(SchoolData);
        result.setTeachernum(TeacherData);
        result.setStudentnum(StudentData);
        result.setFixedassets(FixedAssets);
        return result;
    }
}

// SchoolFeature.java

