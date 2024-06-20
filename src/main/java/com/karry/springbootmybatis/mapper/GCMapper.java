package com.karry.springbootmybatis.mapper;

import com.karry.springbootmybatis.pojo.GiniCoefficient;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GCMapper {
    @Select("select * from \"gcjhs\"" )
    List<GiniCoefficient> listfcec();

}
