package com.karry.springbootmybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface HumanRMapper {
    @Select("SELECT * FROM \"Geni\".\"human_res\"")
    List<Map<String, Object>> getAll();

    @Select("SELECT * FROM \"Geni\".\"human_res\" WHERE year = #{year} AND stage = #{stage} AND location = #{location}")
    List<Map<String, Object>> getFilteredData(@Param("year") Integer year, @Param("stage") String stage, @Param("location") String location);

    @Select("SELECT \"EduCost\" FROM \"Geni\".\"financial_res\" WHERE year = 2022")
    List<Map<String, Object>> getEduCost();
}