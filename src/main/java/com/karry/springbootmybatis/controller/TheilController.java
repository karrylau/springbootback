package com.karry.springbootmybatis.controller;

import com.karry.springbootmybatis.mapper.TheilMapper;
import com.karry.springbootmybatis.pojo.*;
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
    @PostMapping ("/taierSearch")    //得到年份后，将数据传给后端，从数据库调取数据到前端
    public Map<String, Object> getFinancialData(@RequestBody Map<String,Integer> request) {
        Integer year = request.get("selectedYear");//这里是先提取其中的年份进行操作
        return theilService.getFinancialDataWithProvinces(year);
    }


    @PostMapping("/taierCount")
    public TheilResult getarea(@RequestBody TheilRequest request) {
        System.out.println("原始请求内容：\n" + request);
        System.out.println("Received tuningData: " + request.getTuningData());
        System.out.println("Received location: " + request.getLocation());
        return theilService.getarea(request.getTuningData(), request.getLocation());
    }
}
