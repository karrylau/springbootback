package com.karry.springbootmybatis.mapper;

import com.karry.springbootmybatis.pojo.Theil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface TheilMapper {
    @Select("SELECT \"location\",  \"EduCost\" FROM \"financial_res\" where location !='全国' AND stage='高中' AND \"year\" = #{year}" )
//    +
//            "ORDER BY CASE \"location\" " +
//            "        WHEN '黑龙江' THEN 1 " +
//            "        WHEN '吉林' THEN 2 " +
//            "        WHEN '辽宁' THEN 3 " +
//            "        WHEN '内蒙' THEN 4 " +
//            "        WHEN '东北' THEN 3 " +
//            "        WHEN '北京' THEN 3 " +
//            "        WHEN '天津' THEN 3 " +
//            "        WHEN '陕西' THEN 3 " +
//            "        WHEN '河北' THEN 3 " +
//            "        WHEN '华北' THEN 3 " +
//            "        WHEN '上海' THEN 3 " +
//            "        WHEN '江苏' THEN 3 " +
//            "        WHEN '浙江' THEN 3 " +
//            "        WHEN '安徽' THEN 3 " +
//            "        WHEN '福建' THEN 3 " +
//            "        WHEN '江西' THEN 3 " +
//            "        WHEN '山东' THEN 3 " +
//            "        WHEN '华东' THEN 3 " +
//            "        WHEN '河南' THEN 3 " +
//            "        WHEN '湖南' THEN 3 " +
//            "        WHEN '湖北' THEN 3 " +
//            "        WHEN '广东' THEN 3 " +
//            "        WHEN '广西' THEN 3 " +
//            "        WHEN '海南' THEN 3 " +
//            "        WHEN '华中' THEN 3 " +
//            "        WHEN '四川' THEN 3 " +
//            "        WHEN '重庆' THEN 3 " +
//            "        WHEN '贵州' THEN 3 " +
//            "        WHEN '云南' THEN 3 " +
//            "        WHEN '西藏' THEN 3 " +
//            "        ELSE 4\n" +
//            "        END" )
    List<Theil> getAllFinancialData(Integer year);
}

