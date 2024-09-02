package com.karry.springbootmybatis.mapper;

import com.karry.springbootmybatis.pojo.MaterialResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MaterialResourceMapper {
    @Select("SELECT * FROM \"material resources\"")
    List<MaterialResource> findAll();
}