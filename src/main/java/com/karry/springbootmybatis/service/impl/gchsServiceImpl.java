package com.karry.springbootmybatis.service.impl;

import com.karry.springbootmybatis.pojo.gchs;
import com.karry.springbootmybatis.service.gchsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.karry.springbootmybatis.mapper.gchsMapper;
import java.util.List;

@Service
public class gchsServiceImpl implements gchsService {
    @Autowired
    private gchsMapper gchsMapper;
    @Override
    public List<gchs> listgchs() {
        return gchsMapper.listgchs();
    }

}
