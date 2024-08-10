package com.karry.springbootmybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Map;

@Mapper
public interface HumanRMapper {
    @Select("select * from \"Geni\".\"human_res\"")
    List<Map<String, Object>> getAll();
}