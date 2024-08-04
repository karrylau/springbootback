package com.karry.springbootmybatis.mapper;

import com.karry.springbootmybatis.pojo.financialRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface FinancialRMapper {
    @Select("select * from \"Geni\".\"financial_res\"" )  //"Geni".为选择架构（我把表放在了一个新架构里），如果需要在自己电脑测试请去掉
    List<financialRes> getAll();
}
