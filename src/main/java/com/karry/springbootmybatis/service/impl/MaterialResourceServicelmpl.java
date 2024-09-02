package com.karry.springbootmybatis.service.impl;

import com.karry.springbootmybatis.mapper.MaterialResourceMapper;
import com.karry.springbootmybatis.pojo.MaterialResource;
import com.karry.springbootmybatis.service.MaterialResourceService;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MaterialResourceServicelmpl implements MaterialResourceService{
    private  MaterialResourceMapper materialResourceMapper;

    public MaterialResourceServicelmpl(MaterialResourceMapper materialResourceMapper)
    {
        this.materialResourceMapper = materialResourceMapper;
    }
    @Override
    public List<MaterialResource> getAllMaterialResources()
    {
        return materialResourceMapper.findAll();
    }

    @Override
    public Map<String, List<Object>> getStructuredMaterialResources() {
        List<MaterialResource> resources = getAllMaterialResources();
        Map<String, List<Object>> structuredData = new LinkedHashMap<>();
        List<Object> years = new ArrayList<>();
        List<Object> locations = new ArrayList<>();
        List<Object> stages = new ArrayList<>();
        List<Object> schools = new ArrayList<>();
        List<Object> onlineCovers = new ArrayList<>();
        List<Object> bookCovers = new ArrayList<>();
        List<Object> areas = new ArrayList<>();
        List<Object> fixedAssets = new ArrayList<>();

        for (MaterialResource resource : resources) {
            years.add(resource.getYear());
            locations.add(resource.getLocation());
            stages.add(resource.getStage());
            schools.add(resource.getSchool());
            onlineCovers.add(resource.getBooksPerStudent());
            bookCovers.add(resource.getComputersPerStudent());
            areas.add(resource.getArea());
            fixedAssets.add(resource.getFixedAssets());
        }

        structuredData.put("year", years);
        structuredData.put("location", locations);
        structuredData.put("stage", stages);
        structuredData.put("school", schools);
        structuredData.put("BooksPerStudent", onlineCovers);
        structuredData.put("ComputersPerStudent", bookCovers);
        structuredData.put("area", areas);
        structuredData.put("fixedAssets", fixedAssets);

        return structuredData;
    }
}