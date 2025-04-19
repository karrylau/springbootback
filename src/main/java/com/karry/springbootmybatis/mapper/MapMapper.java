package com.karry.springbootmybatis.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Map;

@Mapper
public interface MapMapper {
    @Select("SELECT * FROM \"map\" WHERE weight IS NOT NULL")
    List<Map<String, Object>> getAllSchools();

    @Select("SELECT * FROM \"map\" WHERE weight IS NULL ORDER BY location ASC")
    List<Map<String, Object>> getCoordinate();

    @Select("SELECT location,\"Tnum\",\"Snum\" FROM \"human_res\" WHERE year=#{year} AND stage='小学' ORDER BY location ASC")
    List<Map<String, Object>> getNum(@Param("year")Integer year);

    @Select("SELECT location,fixedassets FROM \"material resources\" WHERE year=#{year} AND stage='小学' ORDER BY location ASC")
    List<Map<String, Object>> getFixed(@Param("year")Integer year);

    @Select("SELECT location,stage,gdp,\"CulCost\",\"EduCost\",\"PubCost\" FROM \"financial_res\" WHERE year=#{year} ORDER BY location ASC")
    List<Map<String, Object>> getBlocknum1(@Param("year")Integer year);

    @Select("SELECT location,stage,\"Tnum\",\"Snum\",\"STratio\" FROM \"human_res\" WHERE year=#{year} ORDER BY location ASC")
    List<Map<String, Object>> getBlocknum2(@Param("year")Integer year);

    @Select("SELECT location,\"primary\",middle,senior FROM \"SchoolNum\" WHERE year=#{year} ORDER BY location ASC")
    List<Map<String, Object>> getBlocknum3(@Param("year")Integer year);
}