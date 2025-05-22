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

//    @Select("SELECT \"EduCost\" FROM \"financial_res\" WHERE year = #{year}")
//    List<Map<String, Object>> getEduCost(@Param("year") Integer year);

    @Select("SELECT \"EduCost\" FROM \"financial_res\" WHERE year = #{year} AND location= '全国' ")
    List<Map<String, Object>> getEduCost(@Param("year") Integer year);
    @Select("SELECT \"gdp\" FROM \"financial_res\" WHERE year = #{year} AND location = '全国'")//这里要指出，这里的location是一个字符串，所以要用单引号括起来，全国是location
    List<Map<String, Object>> getNationalGDP(@Param("year") Integer year);

//    @Select("SELECT \"CulCost\" FROM \"financial_res\" WHERE year = #{year}")//这里的year是一个数字，所以不需要单引号
//    List<Map<String, Object>> getCulCost(@Param("year") Integer year);

    @Select("SELECT \"CulCost\" FROM \"financial_res\" WHERE year = #{year} AND location= '全国'")//这里的year是一个数字，所以不需要单引号
    List<Map<String, Object>> getCulCost(@Param("year") Integer year);

    @Select("SELECT \"area\" FROM \"material resources\" WHERE year = #{year} AND location='全国'and stage IS NULL")//这里的year是一个数字，所以不需要单引号
    List<Map<String, Object>> getArea(@Param("year") Integer year);

    @Select("SELECT \"fixedassets\" FROM \"material resources\" WHERE year = #{year} AND location = '全国'and stage IS NULL")
    List<Map<String, Object>> getfixedassets(@Param("year") Integer year);

    @Select("SELECT \"Snum\" FROM \"human_res\" WHERE stage='小学' AND location=#{province} AND year IN (2018, 2019, 2020, 2021, 2022) ORDER BY year ASC")
    List<Map<String, Object>> getPrimarySchoolSnum(@Param("province") String province);

    @Select("SELECT \"Snum\" FROM \"human_res\" WHERE stage='初中' AND location=#{province} AND year IN (2018, 2019, 2020, 2021, 2022) ORDER BY year ASC")
    List<Map<String, Object>> getMiddleSchoolSnum(@Param("province") String province);

    @Select("SELECT \"Snum\" FROM \"human_res\" WHERE stage='高中' AND location=#{province} AND year IN (2018, 2019, 2020, 2021, 2022) ORDER BY year ASC")
    List<Map<String, Object>> getHighSchoolSnum(@Param("province") String province);

    @Select("SELECT \"Tnum\" FROM \"human_res\" WHERE stage='小学' AND location=#{province} AND year IN (2018, 2019, 2020, 2021, 2022) ORDER BY year ASC")
    List<Map<String, Object>> getPrimarySchoolTnum(@Param("province") String province);

    @Select("SELECT \"Tnum\" FROM \"human_res\" WHERE stage='初中' AND location=#{province} AND year IN (2018, 2019, 2020, 2021, 2022) ORDER BY year ASC")
    List<Map<String, Object>> getMiddleSchoolTnum(@Param("province") String province);

    @Select("SELECT \"Tnum\" FROM \"human_res\" WHERE stage='高中' AND location=#{province} AND year IN (2018, 2019, 2020, 2021, 2022) ORDER BY year ASC")
    List<Map<String, Object>> getHighSchoolTnum(@Param("province") String province);
}