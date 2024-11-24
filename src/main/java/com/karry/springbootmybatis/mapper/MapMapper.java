package com.karry.springbootmybatis.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Map;

@Mapper
public interface MapMapper {
    @Select("SELECT * FROM \"map\"")
    List<Map<String, Object>> getAllSchools();

    @Select("SELECT school, longitude, latitude, weight FROM \"map\" ")
    List<Map<String, Object>> getCoordinate();

    @Select("SELECT school, longitude, latitude, weight FROM \"map\" ")
    List<Map<String, Object>> getTeacherNum();

}