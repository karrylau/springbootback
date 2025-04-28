package com.karry.springbootmybatis.service;

import com.karry.springbootmybatis.pojo.homenum;
import com.karry.springbootmybatis.pojo.humanRes;
import com.karry.springbootmybatis.pojo.fixeddong;
import com.karry.springbootmybatis.pojo.numdong;

public interface HumanRService {
    humanRes getAll();
    humanRes getFilteredData(Integer year, String stage, String location);
    homenum getHomenum(Integer year);
    numdong getSnum();
    numdong getTnum();
}