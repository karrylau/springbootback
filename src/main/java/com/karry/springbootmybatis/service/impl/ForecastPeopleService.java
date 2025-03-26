package com.karry.springbootmybatis.service.impl;

import com.karry.springbootmybatis.mapper.ForecastPeopleMapper;
import com.karry.springbootmybatis.pojo.EducationPopulation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ForecastPeopleService {
    private final ForecastPeopleMapper mapper;
    private static final int MIN_YEAR = 2010;  // 数据库最早记录年份
    private static final int MAX_BACK_YEAR = 10;  // 最大回溯年数

    public ForecastPeopleService(ForecastPeopleMapper mapper) {
        this.mapper = mapper;
    }

    // 动态回溯查询有效数据
    private int getValidData(int targetYear, String location, int offset, int maxBack) {
        for (int i = 0; i <= maxBack; i++) {
            int year = targetYear - offset - i;
            if (year < MIN_YEAR) continue;
            Optional<EducationPopulation> data = mapper.findByYearAndLocation(year, location);
            if (data.isPresent()) {
                return data.get().getJuniorInSchool();
            }
        }
        return 0;
    }

    // 获取指定地区和年份范围的数据
    private List<EducationPopulation> getRangeData(int startYear, int endYear, String location) {
        return mapper.findByYearBetweenAndLocationOrderByYearAsc(startYear, endYear, location);
    }

    // 计算小学入学人数
    public int calculatePrimaryEnrollment(int targetYear, String location) {
        int startYear = Math.max(targetYear - 6, MIN_YEAR);
        return getRangeData(startYear, targetYear, location).stream()
                .mapToInt(EducationPopulation::getBirthNum)
                .sum();
    }

    // 计算初中入学人数
    public int calculateJuniorEnrollment(int targetYear, String location) {
        int startYear = Math.max(targetYear - 9, MIN_YEAR);
        return getRangeData(startYear, targetYear, location).stream()
                .mapToInt(EducationPopulation::getBirthNum)
                .sum();
    }

    // 计算高中入学人数
    public int calculateSeniorEnrollment(int targetYear, String location) {
        int juniorGraduate = getValidData(targetYear, location, 3, MAX_BACK_YEAR);
        return juniorGraduate / 2;
    }

    // 计算小学在籍人数
    public int calculatePrimaryInSchool(int targetYear, String location) {
        int enrollment = calculatePrimaryEnrollment(targetYear, location);
        int graduate = calculateJuniorEnrollment(targetYear, location);

        Optional<EducationPopulation> lastYearData = getLastYearData(targetYear, location);
        return lastYearData.map(data -> data.getPrimaryInSchool() + enrollment - graduate)
                .orElse(enrollment);
    }

    // 计算初中在籍人数
    public int calculateJuniorInSchool(int targetYear, String location) {
        int enrollment = calculateJuniorEnrollment(targetYear, location);
        int graduate = getValidData(targetYear, location, 3, MAX_BACK_YEAR);

        Optional<EducationPopulation> lastYearData = getLastYearData(targetYear, location);
        return lastYearData.map(data -> data.getJuniorInSchool() + enrollment - graduate)
                .orElse(enrollment);
    }

    // 计算高中在籍人数
    public int calculateSeniorInSchool(int targetYear, String location) {
        int startYear = Math.max(targetYear - 3, MIN_YEAR);
        int sumBirth = getRangeData(startYear, targetYear, location).stream()
                .mapToInt(EducationPopulation::getBirthNum)
                .sum();
        return sumBirth / 2;
    }

    // 获取上一年有效数据（允许回溯3年）
    private Optional<EducationPopulation> getLastYearData(int targetYear, String location) {
        for (int i = 0; i <= 3; i++) {
            int year = targetYear - 1 - i;
            if (year < MIN_YEAR) continue;
            Optional<EducationPopulation> data = mapper.findByYearAndLocation(year, location);
            if (data.isPresent()) {
                return data;
            }
        }
        return Optional.empty();
    }
}