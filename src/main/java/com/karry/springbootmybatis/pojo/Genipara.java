package com.karry.springbootmybatis.pojo;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Genipara {
    private Integer[] snum;
    private Double[] cost;
    private Integer allpeople;
    private Double allmoney;
}
