package com.karry.springbootmybatis.pojo;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Genicalculate {
    private Integer startyear;
    private Integer endyear;
    private Genipara[] para;
}
