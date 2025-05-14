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

    @Select("SELECT \"location\", \"Snum\" FROM \"human_res\"  where location IN ('盐城市', '淮安市', '宿迁市', '连云港市', '徐州市', '扬州市', '泰州市', '南通市', '南京市', '镇江市', '苏州市', '无锡市', '常州市') AND stage = '高中' AND \"year\" = #{year}")//获取的是人数，虽然好像用不到
    List<Map<String, Integer>> getStudentDataJiangSu(Integer year);

    @Select("SELECT \"location\", \"Snum\" FROM \"human_res\" where location IN ('海陵区', '高港区', '姜堰区', '兴化市', '靖江市', '泰兴市') AND stage = '高中' AND \"year\" = #{year}")//获取的是人数，虽然好像用不到
    List<Map<String, Integer>> getStudentDataTaiZhou(Integer year);

    @Select("SELECT \"location\", \"Snum\" FROM \"human_res\" where location IN ( '戴窑镇', '合陈镇', '永丰镇', '新垛镇', '安丰镇', '海南镇', '钓鱼镇', '大邹镇', '沙沟镇', '中堡镇', '李中镇', '西郊镇', '临城镇', '垛田镇', '竹泓镇', '沈伦镇', '大垛镇', '荻垛镇', '陶庄镇', '昌荣镇', '茅山镇', '周庄镇', '陈堡镇', '戴南镇', '张郭镇', '昭阳镇', '大营镇', '下圩镇', '城东镇', '老圩乡', '周奋乡', '缸顾乡', '西鲍乡', '林湖乡') AND stage = '高中' AND \"year\" = #{year}")//获取的是人数，虽然好像用不到
    List<Map<String, Integer>> getStudentDataXingHua(Integer year);

    //@Select("SELECT \"location\", \"Snum\" FROM \"human_res\" where location !='全国' AND stage = '高中' AND \"year\" = #{year}")//获取的是人数，虽然好像用不到，而且选的是高中，学段是确定的
    // List<Map<String,Integer>> Stunum(Integer year);  //新实验，发现使用map<string,integer>不能直接通过mapper导入，智能用list
}

