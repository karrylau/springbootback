package com.karry.springbootmybatis.controller;

import com.karry.springbootmybatis.pojo.MaterialResource;
import com.karry.springbootmybatis.service.impl.MaterialResourceServicelmpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
@RestController
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
}