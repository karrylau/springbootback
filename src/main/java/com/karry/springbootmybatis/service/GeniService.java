package com.karry.springbootmybatis.service;

import com.karry.springbootmybatis.pojo.Geniresult;
import com.karry.springbootmybatis.pojo.Geniselect;
import com.karry.springbootmybatis.pojo.peopleselect;
import com.karry.springbootmybatis.pojo.timeselect;


public interface GeniService {
    Geniresult getGeni();

    Geniresult getGeni(Geniselect geniselect);
}
