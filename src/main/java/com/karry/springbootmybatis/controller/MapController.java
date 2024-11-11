package com.karry.springbootmybatis.controller;
import com.karry.springbootmybatis.service.MapService;
import com.karry.springbootmybatis.pojo.SchoolFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/dataset")
    public Map<String, List<SchoolFeature>> getSchoolDataset() {
        return schoolService.getSchoolDataset();
    }
}
