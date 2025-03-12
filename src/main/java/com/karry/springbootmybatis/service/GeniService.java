package com.karry.springbootmybatis.service;

import com.karry.springbootmybatis.pojo.*;

import java.util.List;
import java.util.Map;


public interface GeniService {
    Geniresult getGeni();

    Geniresult getGeni(Geniselect geniselect);

    geniCalResult GeniCalculate(Genicalculate genicalculate);

   List<Map<String,Searchresult>> SearchGeni(Integer year);
}
