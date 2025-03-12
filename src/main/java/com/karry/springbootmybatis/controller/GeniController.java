package com.karry.springbootmybatis.controller;

import com.karry.springbootmybatis.pojo.*;
import com.karry.springbootmybatis.service.GeniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GeniController {

    @Autowired
    private GeniService geniService;

//    @PostMapping("/geniSearch")  //接受一个年份，返回各省份及其基尼系数，省份键，数据值（各金额和基尼系数）
//    public Geniresult getGeniselect(@RequestBody Geniselect geniselect) {
//        return geniService.getGeni(geniselect);
//    }

    @PostMapping("/geniSearch")  //接受一个年份，返回各省份及其基尼系数，省份键，数据值（各金额和基尼系数）
    public List<Map<String,Searchresult>> getGeniselect(@RequestBody Integer year) {
        return geniService.SearchGeni(year);
    }
    @PostMapping("/genicalculate")
    public geniCalResult getGenicalculate(@RequestBody Genicalculate genicalculate) {
        return geniService.GeniCalculate(genicalculate);
    }
}