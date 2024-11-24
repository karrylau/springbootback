package com.karry.springbootmybatis.service;

import com.karry.springbootmybatis.pojo.*;
import java.util.List;
import java.util.Map;

public interface MaterialResourceService
{
    List<MaterialResource> getAllMaterialResources();
    Map<String, List<Object>> getStructuredMaterialResources(); // 新方法

    fixeddong getFixedData();
    numdong getSchoolData();
}
