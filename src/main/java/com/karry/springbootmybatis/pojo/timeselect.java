package com.karry.springbootmybatis.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class timeselect {
    private Integer name;
    private String region;
    private Integer startYear;
    private Integer endYear;
    private String partition;
    private String province;

    public timeselect() {
    }
}