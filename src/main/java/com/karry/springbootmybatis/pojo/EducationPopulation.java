package com.karry.springbootmybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationPopulation {
    private String location;   // 地区
    private int year;          // 年份
    private int birthNum;      // 当年出生人口数
    private int primaryInSchool; // 小学在籍人数
    private int juniorInSchool;  // 初中在籍人数
    private int seniorInSchool;  // 高中在籍人数
}