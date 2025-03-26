package com.karry.springbootmybatis.mapper;

import com.karry.springbootmybatis.pojo.EducationPopulation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ForecastPeopleMapper {

    // 查询指定年份和地区的数据
    @Select("SELECT * FROM forcastpeople WHERE year = #{year} AND location = #{location}")
    Optional<EducationPopulation> findByYearAndLocation(@Param("year") int year, @Param("location") String location);

    // 查询指定年份和地区之前的历史数据
    @Select("SELECT * FROM forcastpeople WHERE year <= #{year} AND location = #{location} ORDER BY year ASC")
    List<EducationPopulation> findByYearLessThanEqualAndLocationOrderByYearAsc(
            @Param("year") int year,
            @Param("location") String location
    );

    // 查询指定地区和年份范围的数据
    @Select("SELECT * FROM forcastpeople " +
            "WHERE year BETWEEN #{startYear} AND #{endYear} " +
            "AND location = #{location} " +
            "ORDER BY year ASC")
    List<EducationPopulation> findByYearBetweenAndLocationOrderByYearAsc(
            @Param("startYear") int startYear,
            @Param("endYear") int endYear,
            @Param("location") String location
    );
}