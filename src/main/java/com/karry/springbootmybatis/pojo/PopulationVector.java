package com.karry.springbootmybatis.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PopulationVector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double vectorValue;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getVectorValue() {
        return vectorValue;
    }

    public void setVectorValue(double vectorValue) {
        this.vectorValue = vectorValue;
    }
}