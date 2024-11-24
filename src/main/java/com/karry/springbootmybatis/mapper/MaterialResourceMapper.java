package com.karry.springbootmybatis.mapper;

import com.karry.springbootmybatis.pojo.MaterialResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface MaterialResourceMapper {
    @Select("SELECT * FROM \"material resources\"")
    List<MaterialResource> findAll();

    @Select("SELECT \"fixedassets\" FROM \"material resources\" WHERE location = '全国' AND stage = '小学' AND year IN (2016, 2017, 2018, 2019, 2020) ORDER BY year ASC")
    List<Map<String, Object>> getPrimarySchoolData();

    @Select("SELECT \"fixedassets\" FROM \"material resources\" WHERE location = '全国' AND stage = '初中' AND year IN (2016, 2017, 2018, 2019, 2020) ORDER BY year ASC")
    List<Map<String, Object>> getMiddleSchoolData();

    @Select("SELECT \"fixedassets\" FROM \"material resources\" WHERE location = '全国' AND stage = '高中' AND year IN (2016, 2017, 2018, 2019, 2020) ORDER BY year ASC")
    List<Map<String, Object>> getHighSchoolData();

    @Select("SELECT * FROM \"SchoolNum\" WHERE location = '总计' AND year IN (2016, 2017, 2018, 2019, 2020) ORDER BY year ASC")
    List<Map<String, Object>> getSchoolNumData();
}