package com.karry.springbootmybatis.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Map;

@Mapper
public interface MapMapper {
    @Select("SELECT * FROM \"map\" WHERE weight IS NOT NULL")
    List<Map<String, Object>> getAllSchools();

    @Select("SELECT * FROM \"map\" WHERE weight IS NULL ORDER BY location ASC")
    List<Map<String, Object>> getCoordinate();

    @Select("SELECT location,\"Tnum\",\"Snum\" FROM \"human_res\" WHERE year=#{year} AND stage='小学' ORDER BY location ASC") //ASC排序确保每一个都对应上
    List<Map<String, Object>> getNum(@Param("year")Integer year);

    @Select("SELECT location,fixedassets FROM \"material resources\" WHERE year=#{year} AND stage='小学' ORDER BY location ASC")
    List<Map<String, Object>> getFixed(@Param("year")Integer year);

    @Select("SELECT location,stage,gdp,\"CulCost\",\"EduCost\",\"PubCost\" FROM \"financial_res\" WHERE year=#{year} AND location IN ( '江苏省', '山东省', '河南省', '湖北省', '湖南省', '广东省','广西壮族自治区', '海南省', '重庆市', '四川省', '贵州省','云南省', '西藏自治区', '台湾省', '陕西省', '甘肃省', '青海省', '宁夏回族自治区', '新疆维吾尔自治区', '澳门', '北京市', '香港','天津市', '河北省', '山西省', '内蒙古自治区', '辽宁省', '吉林省','黑龙江省', '上海市', '浙江省', '安徽省', '福建省', '江西省')   ORDER BY location ASC")
    List<Map<String, Object>> getBlocknum1(@Param("year")Integer year);

    @Select("SELECT location,stage,\"Tnum\",\"Snum\",\"STratio\" FROM \"human_res\" WHERE year=#{year} AND location IN ( '江苏省', '山东省', '河南省', '湖北省', '湖南省', '广东省','广西壮族自治区', '海南省', '重庆市', '四川省', '贵州省','云南省', '西藏自治区', '台湾省', '陕西省', '甘肃省', '青海省', '宁夏回族自治区', '新疆维吾尔自治区', '澳门', '北京市', '香港','天津市', '河北省', '山西省', '内蒙古自治区', '辽宁省', '吉林省','黑龙江省', '上海市', '浙江省', '安徽省', '福建省', '江西省')  ORDER BY location ASC")
    List<Map<String, Object>> getBlocknum2(@Param("year")Integer year);

    @Select("SELECT location,\"primary\",middle,senior FROM \"SchoolNum\" WHERE year=#{year} AND location IN ( '江苏省', '山东省', '河南省', '湖北省', '湖南省', '广东省','广西壮族自治区', '海南省', '重庆市', '四川省', '贵州省','云南省', '西藏自治区', '台湾省', '陕西省', '甘肃省', '青海省', '宁夏回族自治区', '新疆维吾尔自治区', '澳门', '北京市', '香港','天津市', '河北省', '山西省', '内蒙古自治区', '辽宁省', '吉林省','黑龙江省', '上海市', '浙江省', '安徽省', '福建省', '江西省')  ORDER BY location ASC")
    List<Map<String, Object>> getBlocknum3(@Param("year")Integer year);

}