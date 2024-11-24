package com.karry.springbootmybatis.service;

import com.karry.springbootmybatis.pojo.Theil;

import java.util.List;
import java.util.Map;

public interface TheilService {
    Map<String, Object> getFinancialDataWithProvinces(Integer year);
    Map<String,Object > getarea(Integer year);
}
