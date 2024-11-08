package com.karry.springbootmybatis.service.impl;

import com.karry.springbootmybatis.mapper.FinancialRMapper;
import com.karry.springbootmybatis.pojo.financialRes;
import com.karry.springbootmybatis.service.FinancialRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FinancialRServiceImpl implements FinancialRService {

    @Autowired
    private FinancialRMapper financialRMapper;

    @Override
    public financialRes getAll() {
        try {
            List<Map<String, Object>> rawData = financialRMapper.getAll();
            financialRes result = new financialRes();

            result.setYear(new ArrayList<>());
            result.setLocation(new ArrayList<>());
            result.setStage(new ArrayList<>());
            result.setGdp(new ArrayList<>());
            result.setCulCost(new ArrayList<>());
            result.setEduCost(new ArrayList<>());
            result.setPubCost(new ArrayList<>());
            result.setAllCost(new ArrayList<>());

            for (Map<String, Object> entry : rawData) {
                result.getYear().add((Integer) entry.get("year"));
                result.getGdp().add((Double) entry.get("gdp"));
                result.getCulCost().add((Double) entry.get("culCost"));
                result.getEduCost().add((Double) entry.get("eduCost"));
                result.getPubCost().add((Double) entry.get("pubCost"));
                result.getAllCost().add((Double) entry.get("allCost"));
                result.getLocation().add((String) entry.get("location"));
                result.getStage().add((String) entry.get("stage"));
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new financialRes();
        }
    }
}