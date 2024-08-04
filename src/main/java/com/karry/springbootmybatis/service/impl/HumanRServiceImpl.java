package com.karry.springbootmybatis.service.impl;

import com.karry.springbootmybatis.mapper.HumanRMapper;
import com.karry.springbootmybatis.pojo.humanRes;
import com.karry.springbootmybatis.service.HumanRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HumanRServiceImpl implements HumanRService {

    @Autowired
    private HumanRMapper humanRMapper;

    @Override
    public List<humanRes> getAll() {
        return humanRMapper.getAll();
    }
}