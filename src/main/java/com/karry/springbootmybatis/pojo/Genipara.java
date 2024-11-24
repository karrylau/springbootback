package com.karry.springbootmybatis.pojo;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Genipara {
    private Integer[] pnum;
    private Integer psum;
    private Double[] mnum;
    private Double msum;
}
