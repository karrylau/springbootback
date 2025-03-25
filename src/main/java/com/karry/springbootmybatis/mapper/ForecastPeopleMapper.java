package com.karry.springbootmybatis.mapper;

import com.karry.springbootmybatis.pojo.EducationPopulation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ForecastPeopleMapper {

    /**
     * 根据年份和地区查询数据
     * @param year 目标年份
     * @param location 目标地区
     * @return 符合条件的 EducationPopulation 实体
     */
    @Select("SELECT * FROM forcastpeople WHERE year = #{year} AND location = #{location}")
    Optional<EducationPopulation> findByYearAndLocation(@Param("year") int year, @Param("location") String location);

    /**
     * 查询指定年份和地区之前的历史数据（按年份升序排列）
     * @param year 目标年份
     * @param location 目标地区
     * @return 历史数据列表
     */
    @Select("SELECT * FROM forcastpeople WHERE year <= #{year} AND location = #{location} ORDER BY year ASC")
    List<EducationPopulation> findByYearLessThanEqualAndLocationOrderByYearAsc(@Param("year") int year, @Param("location") String location);
}