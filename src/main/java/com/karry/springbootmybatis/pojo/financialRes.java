package com.karry.springbootmybatis.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
public class financialRes {
    private List<Integer> year;
    private List<String> location;
    private List<String> stage;
    private List<Double> gdp;
    private List<Double> culCost;
    private List<Double> eduCost;
    private List<Double> pubCost;
    private List<Double> allCost;
}
