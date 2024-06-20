package com.karry.springbootmybatis.service.impl;
import com.karry.springbootmybatis.mapper.GCMapper;
import com.karry.springbootmybatis.pojo.GiniCoefficient;
import com.karry.springbootmybatis.service.GCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class GCServiceImpl implements GCService {
    @Autowired
    private GCMapper giniCoefficientMapper;

    @Override
    public List<GiniCoefficient> listfcec() {

        return giniCoefficientMapper.listfcec();
    }
}
