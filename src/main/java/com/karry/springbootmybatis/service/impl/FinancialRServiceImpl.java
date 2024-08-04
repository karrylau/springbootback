package com.karry.springbootmybatis.service.impl;

import com.karry.springbootmybatis.mapper.FinancialRMapper;
import com.karry.springbootmybatis.pojo.financialRes;
import org.springframework.beans.factory.annotation.Autowired;
import com.karry.springbootmybatis.service.FinancialRService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FinancialRServiceImpl implements FinancialRService {

    @Autowired
    private FinancialRMapper financialRMapper;

    @Override
    public List<financialRes> getAll() {
        return financialRMapper.getAll();
    }
}
