package com.karry.springbootmybatis.pojo;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Genicalculate {
    //private Integer startyear;
    //private Integer endyear;  //目前只计算一年的基尼系数
    private Genipara shiye;
    private Genipara gongyong;
}
