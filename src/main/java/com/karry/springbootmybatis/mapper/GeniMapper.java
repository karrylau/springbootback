package com.karry.springbootmybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Map;

@Mapper
public interface GeniMapper {

    @Select("SELECT * FROM \"financial_res\" WHERE year = #{year} AND stage = #{stage}")
    List<Map<String, Object>> getGeni1(@Param("year") Integer year, @Param("stage") String stage);

    @Select("SELECT * FROM \"human_res\" WHERE year = #{year} AND stage = #{stage}")
    List<Map<String, Object>> getGeni2(@Param("year") Integer year, @Param("stage") String stage);

    @Select("SELECT * FROM \"financial_res\" WHERE year = #{year} AND stage = #{stage} AND location = #{location}")
    List<Map<String, Object>> getlocationGeni(@Param("year") Integer year, @Param("stage") String stage, @Param("location") String location);
}