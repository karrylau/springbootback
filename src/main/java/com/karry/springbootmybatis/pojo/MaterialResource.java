package com.karry.springbootmybatis.pojo;


// 重命名为 MaterialResource

public class MaterialResource {
    private Integer year;
    private String location;
    private String stage;
    private String school;
    private Double computersPerStudent; // 重命名为 ComputersPerStudent
    private Double booksPerStudent; // 重命名为 BooksPerStudent
    private Double area;
    private Double fixedAssets;

    // 默认构造函数
    public MaterialResource() {
    }

    // 带参数的构造函数
    public MaterialResource(Integer year, String location, String stage, String school, Double computersPerStudent, Double booksPerStudent, Double area, Double fixedAssets) {
        this.year = year;
        this.location = location;
        this.stage = stage;
        this.school = school;
        this.computersPerStudent = computersPerStudent;
        this.booksPerStudent = booksPerStudent;
        this.area = area;
        this.fixedAssets = fixedAssets;
    }

    // Getter 和 Setter 方法
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Double getComputersPerStudent() {
        return computersPerStudent;
    }

    public void setComputersPerStudent(Double computersPerStudent) {
        this.computersPerStudent = computersPerStudent;
    }

    public Double getBooksPerStudent() {
        return booksPerStudent;
    }

    public void setBooksPerStudent(Double booksPerStudent) {
        this.booksPerStudent = booksPerStudent;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getFixedAssets() {
        return fixedAssets;
    }

    public void setFixedAssets(Double fixedAssets) {
        this.fixedAssets = fixedAssets;
    }
}
