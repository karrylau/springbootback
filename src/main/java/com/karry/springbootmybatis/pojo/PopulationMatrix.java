package com.karry.springbootmybatis.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class PopulationMatrix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int matrixRow;
    private int matrixCol;
    private double matrixValue;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMatrixRow() {
        return matrixRow;
    }

    public void setMatrixRow(int matrixRow) {
        this.matrixRow = matrixRow;
    }

    public int getMatrixCol() {
        return matrixCol;
    }

    public void setMatrixCol(int matrixCol) {
        this.matrixCol = matrixCol;
    }

    public double getMatrixValue() {
        return matrixValue;
    }

    public void setMatrixValue(double matrixValue) {
        this.matrixValue = matrixValue;
    }
}