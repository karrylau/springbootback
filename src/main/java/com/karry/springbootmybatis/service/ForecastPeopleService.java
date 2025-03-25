package com.karry.springbootmybatis.service;

import com.karry.springbootmybatis.mapper.ForecastPeopleMapper;
import com.karry.springbootmybatis.pojo.EducationPopulation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ForecastPeopleService {
    private final ForecastPeopleMapper mapper;

    public ForecastPeopleService(ForecastPeopleMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 获取指定地区和年份之前的历史数据
     * @param targetYear 目标年份
     * @param location 目标地区
     * @return 历史数据列表
     */
    private List<EducationPopulation> getLocationData(int targetYear, String location) {
        return mapper.findByYearLessThanEqualAndLocationOrderByYearAsc(targetYear, location);
    }

    /**
     * 计算指定地区和年份的小学入学人数
     * @param targetYear 目标年份
     * @param location 目标地区
     * @return 小学入学人数
     */
    public int calculatePrimaryEnrollment(int targetYear, String location) {
        List<EducationPopulation> dataList = getLocationData(targetYear, location);
        System.out.println(dataList);
        return dataList.stream()
                .filter(d -> d.getYear() > targetYear - 6)
                .mapToInt(EducationPopulation::getBirthNum)
                .sum();
    }

    /**
     * 计算指定地区和年份的初中入学人数
     * @param targetYear 目标年份
     * @param location 目标地区
     * @return 初中入学人数
     */
    public int calculateJuniorEnrollment(int targetYear, String location) {
        List<EducationPopulation> dataList = getLocationData(targetYear, location);
        return dataList.stream()
                .filter(d -> d.getYear() > targetYear - 9)
                .mapToInt(EducationPopulation::getBirthNum)
                .sum();
    }

    /**
     * 计算指定地区和年份的高中入学人数
     * @param targetYear 目标年份
     * @param location 目标地区
     * @return 高中入学人数
     */
    public int calculateSeniorEnrollment(int targetYear, String location) {
        int juniorGraduate = mapper.findByYearAndLocation(targetYear - 3, location)
                .map(EducationPopulation::getJuniorInSchool)
                .orElse(0);
        return juniorGraduate / 2;
    }

    /**
     * 计算指定地区和年份的小学在籍人数
     * @param targetYear 目标年份
     * @param location 目标地区
     * @return 小学在籍人数
     */
    public int calculatePrimaryInSchool(int targetYear, String location) {
        int enrollment = calculatePrimaryEnrollment(targetYear, location);
        int graduate = calculateJuniorEnrollment(targetYear, location);

        return mapper.findByYearAndLocation(targetYear - 1, location)
                .map(data -> data.getPrimaryInSchool() + enrollment - graduate)
                .orElse(enrollment);
    }

    /**
     * 计算指定地区和年份的初中在籍人数
     * @param targetYear 目标年份
     * @param location 目标地区
     * @return 初中在籍人数
     */
    public int calculateJuniorInSchool(int targetYear, String location) {
        int enrollment = calculateJuniorEnrollment(targetYear, location);
        int graduate = mapper.findByYearAndLocation(targetYear - 3, location)
                .map(EducationPopulation::getJuniorInSchool)
                .orElse(0);

        return mapper.findByYearAndLocation(targetYear - 1, location)
                .map(data -> data.getJuniorInSchool() + enrollment - graduate)
                .orElse(enrollment);
    }

    /**
     * 计算指定地区和年份的高中在籍人数
     * @param targetYear 目标年份
     * @param location 目标地区
     * @return 高中在籍人数
     */
    public int calculateSeniorInSchool(int targetYear, String location) {
        int sumBirth = getLocationData(targetYear, location).stream()
                .filter(d -> d.getYear() > targetYear - 3)
                .mapToInt(EducationPopulation::getBirthNum)
                .sum();
        return sumBirth / 2;
    }
}