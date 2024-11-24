package com.karry.springbootmybatis.service.impl;

import com.karry.springbootmybatis.mapper.TheilMapper;
import com.karry.springbootmybatis.pojo.Theil;
import com.karry.springbootmybatis.service.TheilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TheilServiceImpl implements TheilService {

    @Autowired
    private TheilMapper theilMapper;

    public Map<String, Object> getFinancialDataWithProvinces(Integer year) {
        // 从数据库获取数据
        List<Theil> financialResList = theilMapper.getAllFinancialData( year);//创建map

        // 创建一个 Map 用于存储省份与教育成本的映射
        Map<String, Double> financialDataMap = new HashMap<>();//province以及教育经费Educost

        // 将数据库中的每一项数据填充到 Map 中
        for (Theil  financialRes : financialResList) {
            financialDataMap.put(financialRes.getLocation(), financialRes.getEduCost());
        }

        // 创建一个包含表头的外层 Map
        Map<String, Object> result = new HashMap<>();
        result.put("provinces", financialDataMap);

        return result;
    }

    public Map<String,Object> getarea(Integer year)
    {
        return null;
    }
//    {
//        List<Theil> financialResList = theilMapper.getAllFinancialData( year);//创建map
//
//    }
}

