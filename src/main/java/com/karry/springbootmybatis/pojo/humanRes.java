package com.karry.springbootmybatis.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
public class humanRes {
    private List<Integer> year;
    private List<String> location;
    private List<String> stage;
    private List<Integer> tNum;
    private List<Integer> sNum;
    private List<Double> STratio;

    public humanRes() {
    }
}

