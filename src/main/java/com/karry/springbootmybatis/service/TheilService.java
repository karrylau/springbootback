package com.karry.springbootmybatis.service;

import com.karry.springbootmybatis.pojo.*;

import java.util.List;
import java.util.Map;

public interface TheilService {
    Map<String, Object> getFinancialDataWithProvinces(Integer year);
    TheilResult getarea (Map<String,Integer> data, String location);  //具体通过getarea实现代码
}
