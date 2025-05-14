package com.karry.springbootmybatis.service.impl;
import com.karry.springbootmybatis.mapper.MapMapper;
import com.karry.springbootmybatis.pojo.*;
import com.karry.springbootmybatis.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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
    public map getMapData(Integer year) {

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
        //System.out.println(coordinate);
        List<Map<String, Object>> teachernum = schoolMapper.getNum(year);
        //System.out.println(teachernum);
        List<Map<String, Object>> fixed = schoolMapper.getFixed(year);
        //System.out.println(coordinate);
        //System.out.println(teachernum);
        //System.out.println(fixed);
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

    @Override
    public Map<String, blocknum> getblocknum(Integer year) {
        List<Map<String, Object>> getF = schoolMapper.getBlocknum1(year);
        List<Map<String, Object>> getH = schoolMapper.getBlocknum2(year);
        List<Map<String, Object>> getS = schoolMapper.getBlocknum3(year);

        Map<String, blocknum> resultMap = new LinkedHashMap<>();
        for(Map<String,Object> dataF : getF) {
            block tf = new block();
            blocknum s = new blocknum();
            String locationF = (String) dataF.get("location");
            tf.setCulcost((Double) dataF.get("CulCost"));
            tf.setEducost((Double) dataF.get("EduCost"));
            tf.setPubcost((Double) dataF.get("PubCost"));
            tf.setGdp((Double)dataF.get("gdp"));

            if (!resultMap.containsKey(locationF)) {
                if (dataF.get("stage").equals("小学")) {
                    s.setPrimary(tf);
                }
                if (dataF.get("stage").equals("初中")) {
                    s.setMiddle(tf);
                }
                if (dataF.get("stage").equals("高中")) {
                    s.setSenior(tf);
                }
                resultMap.put(locationF, s);
            } else {
                if (dataF.get("stage").equals("小学")) {
                    resultMap.get(locationF).setPrimary(tf);
                }
                if (dataF.get("stage").equals("初中")) {
                    resultMap.get(locationF).setMiddle(tf);
                }
                if (dataF.get("stage").equals("高中")) {
                    resultMap.get(locationF).setSenior(tf);
                }
        }
    }



        for(Map<String,Object> dataH : getH){
            String locationH = (String)dataH.get("location");

            if(dataH.get("stage").equals("小学")){
                resultMap.get(locationH).getPrimary().setTnum((Integer)dataH.get("Tnum"));
                resultMap.get(locationH).getPrimary().setSnum((Integer)dataH.get("Snum"));
                resultMap.get(locationH).getPrimary().setStratio((Double)dataH.get("STratio"));
            }
            if(dataH.get("stage").equals("初中")){
                resultMap.get(locationH).getMiddle().setTnum((Integer)dataH.get("Tnum"));
                resultMap.get(locationH).getMiddle().setSnum((Integer)dataH.get("Snum"));
                resultMap.get(locationH).getMiddle().setStratio((Double)dataH.get("STratio"));
            }
            if(dataH.get("stage").equals("高中")){
                resultMap.get(locationH).getSenior().setTnum((Integer)dataH.get("Tnum"));
                resultMap.get(locationH).getSenior().setSnum((Integer)dataH.get("Snum"));
                resultMap.get(locationH).getSenior().setStratio((Double)dataH.get("STratio"));
            }
        }

        //System.out.println(resultMap);
        for(Map<String,Object> dataS : getS){
            String locationS = (String)dataS.get("location");
            if(locationS.equals("总计")){
                continue;
            }
            if(resultMap.get(locationS).getPrimary() == null){
                System.out.print(locationS);
                System.out.print(resultMap.get(locationS));
            }
            resultMap.get(locationS).getPrimary().setSchool((Integer)dataS.get("primary"));
            resultMap.get(locationS).getMiddle().setSchool((Integer)dataS.get("middle"));
            resultMap.get(locationS).getSenior().setSchool((Integer)dataS.get("senior"));
        }
        //System.out.println(resultMap);
        return resultMap;
    }
}

// SchoolFeature.java

