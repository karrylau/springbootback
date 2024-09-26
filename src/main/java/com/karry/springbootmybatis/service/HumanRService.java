package com.karry.springbootmybatis.service;

import com.karry.springbootmybatis.pojo.homenum;
import com.karry.springbootmybatis.pojo.humanRes;

public interface HumanRService {
    humanRes getAll();
    humanRes getFilteredData(Integer year, String stage, String location);
    homenum getHomenum();
}