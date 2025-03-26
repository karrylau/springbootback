package com.karry.springbootmybatis.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class block {
    Double gdp;
    Double culcost;
    Double educost;
    Double pubcost;
    Integer tnum;
    Integer snum;
    Double stratio;
    Integer school;
}
