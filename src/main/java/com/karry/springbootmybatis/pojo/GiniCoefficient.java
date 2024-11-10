package com.karry.springbootmybatis.pojo;

public class GiniCoefficient {

    private Double value;
    private String name;

    public GiniCoefficient() {
    }

    public GiniCoefficient(String name ,Double giniCoefficient) {
        this.value = giniCoefficient;
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}