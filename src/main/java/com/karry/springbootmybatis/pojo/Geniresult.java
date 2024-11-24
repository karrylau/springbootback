package com.karry.springbootmybatis.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Geniresult {
    private Integer[] years;
    private Double[] Geni;

}
