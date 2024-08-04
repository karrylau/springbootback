package com.karry.springbootmybatis.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class financialRes {
    private Integer year;
    private String location;
    private String stage;
    private Double gdp;
    private Double culCost;
    private Double eduCost;
    private Double pubCost;
    private Double allCost;
}
