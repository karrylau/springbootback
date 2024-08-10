package com.karry.springbootmybatis.service.impl;

import com.karry.springbootmybatis.mapper.HumanRMapper;
import com.karry.springbootmybatis.pojo.humanRes;
import com.karry.springbootmybatis.service.HumanRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class HumanRServiceImpl implements HumanRService {

    @Autowired
    private HumanRMapper humanRMapper;

    @Override
    public humanRes getAll() {
        try {
            List<Map<String, Object>> rawData = humanRMapper.getAll();
            humanRes result = new humanRes();

            result.setYear(new ArrayList<>());
            result.setLocation(new ArrayList<>());
            result.setStage(new ArrayList<>());
            result.setTNum(new ArrayList<>());
            result.setSNum(new ArrayList<>());
            result.setSTratio(new ArrayList<>());

            for (Map<String, Object> entry : rawData) {
                result.getYear().add((Integer) entry.get("year"));
                result.getTNum().add((Integer) entry.get("Tnum"));
                result.getSNum().add((Integer) entry.get("Snum"));
                result.getLocation().add((String) entry.get("location"));
                result.getStage().add((String) entry.get("stage"));
                result.getSTratio().add((Double) entry.get("STratio"));
            }

            return result;
        } catch (Exception e) {
            // 记录异常信息
            e.printStackTrace();
            // 返回一个空的humanRes对象或者抛出自定义异常
            return new humanRes();
        }
    }
}