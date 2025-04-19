package com.karry.springbootmybatis.service;
import com.karry.springbootmybatis.pojo.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MapService {
    Map<String, List<SchoolFeature>> getSchoolDataset();
    map getMapData(Integer year);
    Map<String,blocknum> getblocknum(Integer year);
}
