package com.karry.springbootmybatis.service.impl;

import com.karry.springbootmybatis.mapper.ForecastPeopleMapper;
import com.karry.springbootmybatis.pojo.EducationPopulation;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ForecastPeopleService {
    private final ForecastPeopleMapper mapper;
    private static final int MIN_YEAR = 2010;
    private static final int MAX_BACK_YEAR = 10;
    private static final int PREDICT_YEARS = 5; // 预测未来5年

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
        return mapper.findByLocationAndYearRange(location, startYear, endYear);
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

    // 计算师生比（核心方法）
    public Map<Integer, Double> calculateTeacherStudentRatio(int targetYear, String location, String stage) {
        Map<Integer, Double> result = new HashMap<>();

        // 验证输入参数
        if (targetYear < MIN_YEAR) {
            throw new IllegalArgumentException("目标年份不能早于" + MIN_YEAR);
        }
        if (!stage.matches("primary|junior|senior")) {
            throw new IllegalArgumentException("学段必须为primary/junior/senior");
        }

        for (int i = 0; i < PREDICT_YEARS; i++) {
            int currentYear = targetYear + i;
            int studentCount = getStudentCount(currentYear, location, stage);
            int teacherCount = studentCount / 10; // 教师数为学生数的10%

            if (teacherCount == 0) {
                result.put(currentYear, 0.0);
                continue;
            }

            double ratio = (double) studentCount / teacherCount;
            result.put(currentYear, Math.round(ratio * 100.0) / 100.0); // 保留两位小数
        }

        return result;
    }

    // 获取指定年份和学段的学生人数
    private int getStudentCount(int year, String location, String stage) {
        int startYear = year - 1;
        int endYear = year;

        List<EducationPopulation> dataList = mapper.findByLocationAndYearRange(location, startYear, endYear);
        if (dataList.isEmpty()) {
            return 0;
        }

        return switch (stage) {
            case "primary" -> dataList.get(0).getPrimaryInSchool();
            case "junior" -> dataList.get(0).getJuniorInSchool();
            case "senior" -> dataList.get(0).getSeniorInSchool();
            default -> throw new IllegalArgumentException("Invalid stage: " + stage);
        };
    }
}