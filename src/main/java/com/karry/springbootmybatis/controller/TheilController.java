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
   public TheilResult  getarea(@RequestBody Map<String,Integer> data)   //获取的是一整个data，对其进行操作
   {
       return theilService.getarea(data);
   }
}
