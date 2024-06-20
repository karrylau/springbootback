package com.karry.springbootmybatis.pojo;

public class GiniCoefficient {
    private Integer year;
    private Double giniCoefficient;
   public GiniCoefficient() {
    }
    public GiniCoefficient(Integer year, Double giniCoefficient) {
        this.year = year;
        this.giniCoefficient = giniCoefficient;
    }
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public Double getGiniCoefficient() {
        return giniCoefficient;
    }
    public void setGiniCoefficient(Double giniCoefficient) {
        this.giniCoefficient = giniCoefficient;
    }
}
