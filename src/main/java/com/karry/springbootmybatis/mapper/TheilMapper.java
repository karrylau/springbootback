package com.karry.springbootmybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface TheilMapper {
    @Select("SELECT \"location\", \"EduCost\" FROM \"financial_res\" where location !='全国' AND stage='高中' AND \"year\" = #{year}" )//获取的是教育事业费
    List<Map<String, Double>> getAllFinancialData(Integer year);

    @Select("SELECT \"location\", \"Snum\" FROM \"human_res\" where location IN ( '江苏省', '山东省', '河南省', '湖北省', '湖南省', '广东省','广西壮族自治区', '海南省', '重庆市', '四川省', '贵州省','云南省', '西藏自治区', '台湾省', '陕西省', '甘肃省', '青海省', '宁夏回族自治区', '新疆维吾尔自治区', '澳门', '北京市', '香港','天津市', '河北省', '山西省', '内蒙古自治区', '辽宁省', '吉林省','黑龙江省', '上海市', '浙江省', '安徽省', '福建省', '江西省') AND stage = '高中' AND \"year\" = #{year}")//获取的是人数，虽然好像用不到
    List<Map<String, Integer>> getStudentData(Integer year);

    @Select("SELECT \"location\", \"Snum\" FROM \"human_res\" where location !='全国' AND stage = '高中' AND \"year\" = #{year}")//获取的是人数，虽然好像用不到
    List<Map<String, Integer>> getStudentDataJiangSu(Integer year);

    @Select("SELECT \"location\", \"Snum\" FROM \"human_res\" where location !='全国' AND stage = '高中' AND \"year\" = #{year}")//获取的是人数，虽然好像用不到
    List<Map<String, Integer>> getStudentDataTaiZhou(Integer year);

    @Select("SELECT \"location\", \"Snum\" FROM \"human_res\" where location !='全国' AND stage = '高中' AND \"year\" = #{year}")//获取的是人数，虽然好像用不到
    List<Map<String, Integer>> getStudentDataXingHua(Integer year);

    //@Select("SELECT \"location\", \"Snum\" FROM \"human_res\" where location !='全国' AND stage = '高中' AND \"year\" = #{year}")//获取的是人数，虽然好像用不到，而且选的是高中，学段是确定的
   // List<Map<String,Integer>> Stunum(Integer year);  //新实验，发现使用map<string,integer>不能直接通过mapper导入，智能用list
}

