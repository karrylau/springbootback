package com.karry.springbootmybatis.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Map;

@Mapper
public interface MapMapper {
    @Select("SELECT * FROM \"map\" WHERE weight IS NOT NULL")
    List<Map<String, Object>> getAllSchools();

    @Select("SELECT * FROM \"map\" WHERE weight IS NULL ORDER BY location ASC")
    List<Map<String, Object>> getCoordinate();

    @Select("SELECT location,\"Tnum\",\"Snum\" FROM \"human_res\" WHERE year='2021' AND stage='小学' ORDER BY location ASC")
    List<Map<String, Object>> getNum();

    @Select("SELECT location,fixedassets FROM \"material resources\" WHERE year='2021' AND stage='小学' ORDER BY location ASC")
    List<Map<String, Object>> getFixed();
}