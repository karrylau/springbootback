package com.karry.springbootmybatis.pojo;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class homenum {
    private Double gdp;
    private Double allCost;
    private Double culCost;
    private Double area;
    private Double FixedAssets;
}
