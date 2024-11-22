package com.karry.springbootmybatis.service;
import com.karry.springbootmybatis.pojo.SchoolFeature;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public interface MapService {
    Map<String, List<SchoolFeature>> getSchoolDataset();
}
