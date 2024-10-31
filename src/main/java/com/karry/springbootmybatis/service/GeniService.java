package com.karry.springbootmybatis.service;

import com.karry.springbootmybatis.pojo.*;


public interface GeniService {
    Geniresult getGeni();

    Geniresult getGeni(Geniselect geniselect);

    Geniresult GeniCalculate(Genicalculate genicalculate);
}
