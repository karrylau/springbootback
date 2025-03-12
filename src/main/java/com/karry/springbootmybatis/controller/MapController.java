package com.karry.springbootmybatis.controller;
import com.karry.springbootmybatis.service.MapService;
import com.karry.springbootmybatis.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MapController
{
    @Autowired
    private MapService schoolService;

    @PostMapping("/dataset")
    public Map<String, List<SchoolFeature>> getSchoolDataset() {
        return schoolService.getSchoolDataset();
    }

    @PostMapping("/mapdata")  //地图数据传输
    public map getMapData(){
        return schoolService.getMapData();
    }
}
