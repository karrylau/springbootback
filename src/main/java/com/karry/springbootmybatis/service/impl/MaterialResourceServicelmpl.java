package com.karry.springbootmybatis.service.impl;

import com.karry.springbootmybatis.mapper.MaterialResourceMapper;
import com.karry.springbootmybatis.pojo.MaterialResource;
import com.karry.springbootmybatis.pojo.numdong;
import com.karry.springbootmybatis.service.MaterialResourceService;
import org.springframework.stereotype.Service;
import com.karry.springbootmybatis.pojo.fixeddong;
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
    @Override
    public fixeddong getFixedData(String province,Integer year) {
        List<Map<String, Object>> rawData = materialResourceMapper.getPrimarySchoolData(province);
        List<Map<String, Object>> rawData2 = materialResourceMapper.getMiddleSchoolData(province);
        List<Map<String, Object>> rawData3 = materialResourceMapper.getHighSchoolData(province);
        List<Double> highdata = new ArrayList<>();
        List<Double> middleData = new ArrayList<>();
        List<Double> primaryData = new ArrayList<>();
        for (Map<String, Object> data : rawData) {
            primaryData.add((Double) data.get("fixedassets"));
        }
        for (Map<String, Object> data : rawData2) {
            middleData.add((Double) data.get("fixedassets"));
        }
        for (Map<String, Object> data : rawData3) {
            highdata.add((Double) data.get("fixedassets"));
        }

        fixeddong result = new fixeddong();
        // 假设你有方法来将 List<Double> 设置为 primary 属性，或者你可以改造 fixeddong 类来接受 List<Double>
        result.setPrimary(primaryData); // 假设 setPrimary 接受 List<Double>，这需要你在 fixeddong 类中进行相应的修改
        result.setMiddle(middleData);
        result.setHigh(highdata);
        //System.out.println("pd"+result.getPrimary());
        //System.out.println("md"+result.getMiddle());
        //System.out.println("hd"+result.getHigh());
        return result;
    }

    @Override
    public numdong getSchoolData(String province,Integer year)
    {
        List<Map<String, Object>> rawData = materialResourceMapper.getSchoolNumData();
        List<Integer> highdata = new ArrayList<>();
        List<Integer> middleData = new ArrayList<>();
        List<Integer> primaryData = new ArrayList<>();
        for (Map<String, Object> data : rawData) {
            primaryData.add((Integer) data.get("primary"));
            middleData.add((Integer) data.get("middle"));
            highdata.add((Integer) data.get("serior"));
        }
        numdong result = new numdong();
        // 假设你有方法来将 List<Double> 设置为 primary 属性，或者你可以改造 fixeddong 类来接受 List<Double>
        result.setPrimary(primaryData); // 假设 setPrimary 接受 List<Double>，这需要你在 fixeddong 类中进行相应的修改
        result.setMiddle(middleData);
        result.setHigh(highdata);
        //System.out.println("pd"+result.getPrimary());
        //System.out.println("md"+result.getMiddle());
        //System.out.println("hd"+result.getHigh());
        return result;
    }

}