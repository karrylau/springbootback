package com.karry.springbootmybatis.mapper;

import com.karry.springbootmybatis.pojo.gchs;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface gchsMapper {
    @Select("select * from \"gcjhs\"")
    List<gchs> listgchs();

}
