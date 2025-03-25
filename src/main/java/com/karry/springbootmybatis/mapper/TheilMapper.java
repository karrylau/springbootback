package com.karry.springbootmybatis.mapper;

import com.karry.springbootmybatis.pojo.Theil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface TheilMapper {
    @Select("SELECT \"location\", \"EduCost\" FROM \"financial_res\" where location !='全国' AND stage='高中' AND \"year\" = #{year}" )//获取的是教育事业费
    List<Map<String, Double>> getAllFinancialData(Integer year);

    @Select("SELECT \"location\", \"Snum\" FROM \"human_res\" where location !='全国' AND stage = '高中' AND \"year\" = #{year}")//获取的是人数，虽然好像用不到
    List<Map<String, Integer>> getStudentData(Integer year);

    //@Select("SELECT \"location\", \"Snum\" FROM \"human_res\" where location !='全国' AND stage = '高中' AND \"year\" = #{year}")//获取的是人数，虽然好像用不到，而且选的是高中，学段是确定的
   // List<Map<String,Integer>> Stunum(Integer year);  //新实验，发现使用map<string,integer>不能直接通过mapper导入，智能用list
}

