package com.karry.springbootmybatis.mapper;

import com.karry.springbootmybatis.pojo.humanRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface HumanRMapper {
    @Select("select * from \"Geni\".\"human_res\"")  //"Geni".为选择架构（我把表放在了一个新架构里），如果需要在自己电脑测试请去掉
    List<humanRes> getAll();
}