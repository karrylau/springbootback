package com.karry.springbootmybatis.controller;

import com.karry.springbootmybatis.mapper.TheilMapper;
import com.karry.springbootmybatis.pojo.Theil;
import com.karry.springbootmybatis.service.TheilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TheilController {

    @Autowired
    private TheilService theilService;
//    @PostMapping ("/taierSearch")
//    public Map<String, Object> getFinancialData(@RequestParam Integer year) {
//
//        return theilService.getFinancialDataWithProvinces(year);
//    }

//    @PostMapping ("/taierSearch")
//    public Map<String, Object> getArea(@RequestParam Integer year) {
//
//        return theilService.getarea(year);
//    }


    @PostMapping ("/taierSearch")
    public Map<String, Object> getFinancialData(@RequestBody Map<String,Integer> request) {
        Integer year = request.get("year");
        return theilService.getFinancialDataWithProvinces(year);
    }


    //@PostMapping ("/taierCount")
}
