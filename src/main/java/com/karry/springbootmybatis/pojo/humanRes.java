package com.karry.springbootmybatis.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class humanRes {
    private Integer year;
    private String location;
    private String stage;
    private Integer tNum;
    private Integer sNum;
    private Float STratio;

    public humanRes() {
    }
    public humanRes(Integer year, String location, String stage, Integer tNum, Integer sNum, Float STratio) {
        this.year = year;
        this.location = location;
        this.stage = stage;
        this.tNum = tNum;
        this.sNum = sNum;
        this.STratio = STratio;
    }

    @Override
    public String toString() {
        return "humanR{" +
                "year=" + year +
                ", location='" + location + '\'' +
                ", stage=" + stage +
                ", tNum=" + tNum +
                ", sNum='" + sNum + '\'' +
                ", STratio=" + STratio +
                '}';
    }
}

