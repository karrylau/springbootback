package com.karry.springbootmybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface HumanRMapper {
    @Select("SELECT * FROM \"human_res\"")
    List<Map<String, Object>> getAll();

    @Select("SELECT * FROM \"human_res\" WHERE year = #{year} AND stage = #{stage} AND location = #{location}")
    List<Map<String, Object>> getFilteredData(@Param("year") Integer year, @Param("stage") String stage, @Param("location") String location);

    @Select("SELECT \"EduCost\" FROM \"financial_res\" WHERE year = 2022")
    List<Map<String, Object>> getEduCost();

    @Select("SELECT \"gdp\" FROM \"financial_res\" WHERE location = '全国'")//这里要指出，这里的location是一个字符串，所以要用单引号括起来，全国是location
    List<Map<String, Object>> getNationalGDP();

    @Select("SELECT \"CulCost\" FROM \"financial_res\" WHERE year = 2022")//这里的year是一个数字，所以不需要单引号
    List<Map<String, Object>> getCulCost();

    @Select("SELECT \"area\" FROM \"material resources\" WHERE year = 2020 AND location = '全国'")//这里的year是一个数字，所以不需要单引号
    List<Map<String, Object>> getArea();
}