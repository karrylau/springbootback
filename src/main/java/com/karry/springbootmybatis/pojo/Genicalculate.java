package com.karry.springbootmybatis.pojo;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Genicalculate {
    //private Integer startyear;
    //private Integer endyear;  //目前只计算一年的基尼系数
    private Genipara shiye;  //把数据打包好的事业费
    private Genipara gongyong;//把数据打包好的公用费
}
