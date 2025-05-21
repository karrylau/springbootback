package com.karry.springbootmybatis.controller;

import com.karry.springbootmybatis.pojo.Forecast;
import com.karry.springbootmybatis.pojo.MaterialResource;
import com.karry.springbootmybatis.pojo.numdong;
import com.karry.springbootmybatis.service.impl.MaterialResourceServicelmpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.karry.springbootmybatis.pojo.fixeddong;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api")
public class MaterialResourceController {
    private final MaterialResourceServicelmpl materialResourceService;

    public MaterialResourceController(MaterialResourceServicelmpl materialResourceService) {
        this.materialResourceService = materialResourceService;
    }

    @GetMapping("/material-resources")
    public List<MaterialResource> getAllMaterialResources() {
        return materialResourceService.getAllMaterialResources();
    }
    @GetMapping("/structured-material-resources")
    public Map<String, List<Object>> getStructuredMaterialResources() {
        return materialResourceService.getStructuredMaterialResources();
    }
    @GetMapping("/fixeddata")  //固有资产图表连接
    public fixeddong getFixedData(Forecast data) {
        String province = data.getLocation();
        Integer year = data.getTargetYear();
        return materialResourceService.getFixedData(province,year);
    }
    @GetMapping("/schooldata")  //学校数图表连接
    public numdong getSchoolData(Forecast data){
        String province = data.getLocation();
        Integer year = data.getTargetYear();
        return materialResourceService.getSchoolData(province,year);
    }
}