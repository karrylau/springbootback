package com.karry.springbootmybatis.mapper;

import com.karry.springbootmybatis.pojo.OnlineLearningPlatformUsage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OnlineLearningPlatformUsageMapper {
    @Select("SELECT * FROM \"Geni\".\"Online learning platform usage\"")
    List<OnlineLearningPlatformUsage> findAll();
}
