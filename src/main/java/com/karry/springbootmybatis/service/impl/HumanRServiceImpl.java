package com.karry.springbootmybatis.service.impl;

import com.karry.springbootmybatis.mapper.HumanRMapper;
import com.karry.springbootmybatis.pojo.homenum;
import com.karry.springbootmybatis.pojo.humanRes;
import com.karry.springbootmybatis.pojo.fixeddong;
import com.karry.springbootmybatis.pojo.numdong;
import com.karry.springbootmybatis.service.HumanRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class HumanRServiceImpl implements HumanRService {

    @Autowired
    private HumanRMapper humanRMapper;
    @Autowired
    private homenum homenum;

    @Override
    public humanRes getAll() {
        try {
            List<Map<String, Object>> rawData = humanRMapper.getAll();
            humanRes result = new humanRes();

            result.setYear(new ArrayList<>());
            result.setLocation(new ArrayList<>());
            result.setStage(new ArrayList<>());
            result.setTNum(new ArrayList<>());
            result.setSNum(new ArrayList<>());
            result.setSTratio(new ArrayList<>());

            for (Map<String, Object> entry : rawData) {
                result.getYear().add((Integer) entry.get("year"));
                result.getTNum().add((Integer) entry.get("Tnum"));
                result.getSNum().add((Integer) entry.get("Snum"));
                result.getLocation().add((String) entry.get("location"));
                result.getStage().add((String) entry.get("stage"));
                result.getSTratio().add((Double) entry.get("STratio"));
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new humanRes();
        }
    }


    @Override
    public humanRes getFilteredData(Integer year, String stage, String location) {
        try {
            List<Map<String, Object>> rawData = humanRMapper.getFilteredData(year, stage, location);
            humanRes result = new humanRes();

            result.setYear(new ArrayList<>());
            result.setLocation(new ArrayList<>());
            result.setStage(new ArrayList<>());
            result.setTNum(new ArrayList<>());
            result.setSNum(new ArrayList<>());
            result.setSTratio(new ArrayList<>());

            for (Map<String, Object> entry : rawData) {
                result.getYear().add((Integer) entry.get("year"));
                result.getTNum().add((Integer) entry.get("Tnum"));
                result.getSNum().add((Integer) entry.get("Snum"));
                result.getLocation().add((String) entry.get("location"));
                result.getStage().add((String) entry.get("stage"));
                result.getSTratio().add((Double) entry.get("STratio"));
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new humanRes();
        }
    }

    @Override
        public homenum getHomenum(Integer year) {//将有数据的呈现在主页面上
        try {
            List<Map<String, Object>> rawData = humanRMapper.getEduCost(year);//用于获取教育经费
            List<Map<String, Object>> rawData2= humanRMapper.getNationalGDP(year);//用于获取GDP
            List<Map<String, Object>> rawData3= humanRMapper.getCulCost(year);//用于获取文化经费
            List<Map<String, Object>> areadata= humanRMapper.getArea(year);//用于获取面积
            List<Map<String, Object>> rawData4= humanRMapper.getfixedassets(year);
//            System.out.println(rawData);
//            System.out.println(rawData2);
//            System.out.println(rawData3);
//            System.out.println(areadata);
//            System.out.println(rawData4);
            double totalEduCost = 0;
            double gdp = 0;
            double totalCulCost = 0;
            double area=0;
            double fixedassets = 0;
            for (Map<String, Object> entry : rawData) {
                totalEduCost += (Double) entry.get("EduCost");
            }//计算教育经费的总和

            //System.out.println("totalEduCost: " + totalEduCost);
            for (Map<String, Object> entry : rawData3) {
                totalCulCost += (Double) entry.get("CulCost");
            }//计算文化经费的总和

            for (Map<String, Object> entry : areadata) {
                area += (Double) entry.get("area");
            }//计算区域总和
            //System.out.println("area: " + area);

            for (Map<String, Object> entry :rawData4) {
                fixedassets += (Double) entry.get("fixedassets");
            }//计算固有资产总和
             //System.out.println("基础设施: " + fixedassets);

            gdp=(Double) rawData2.get(0).get("gdp");

            homenum result = new homenum();

            result.setAllCost(totalEduCost);

            if (!rawData2.isEmpty()) {
                result.setGdp(gdp);
            } else {
                // 可以设置一个默认值或者进行其他逻辑处理
                result.setGdp(0.0);  // 例如，设为0.0或适当的默认值
            }

            result.setCulCost(totalCulCost);

            result.setArea(area);

            DecimalFormat df = new DecimalFormat("#.00");
            String formattedValue = df.format(fixedassets); // 例如 "325467552.08"
            double roundedValue = Double.parseDouble(formattedValue);
            result.setFixedAssets(roundedValue);

//            System.out.println("totalEduCost: " + result.getFixedAssets());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new homenum();
        }
    }



    @Override
    public numdong getSnum() {//将有数据的呈现在主页面上
        try {
            List<Map<String, Object>> rawData = humanRMapper.getPrimarySchoolSnum();
            List<Map<String, Object>> rawData2= humanRMapper.getMiddleSchoolSnum();
            List<Map<String, Object>> rawData3= humanRMapper.getHighSchoolSnum();
            List<Integer> highdata = new ArrayList<>();
            List<Integer> middleData = new ArrayList<>();
            List<Integer> primaryData = new ArrayList<>();
            for (Map<String, Object> data : rawData) {
                primaryData.add((Integer) data.get("Snum"));
            }
            for (Map<String, Object> data : rawData2) {
                middleData.add((Integer) data.get("Snum"));
            }
            for (Map<String, Object> data : rawData3) {
                highdata.add((Integer) data.get("Snum"));
            }

            numdong result = new numdong();

            result.setPrimary(primaryData);
            result.setMiddle(middleData);
            result.setHigh(highdata);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new numdong();
        }
    }


    @Override
    public numdong getTnum() {//将有数据的呈现在主页面上
        try {
            List<Map<String, Object>> rawData = humanRMapper.getPrimarySchoolTnum();
            List<Map<String, Object>> rawData2= humanRMapper.getMiddleSchoolTnum();
            List<Map<String, Object>> rawData3= humanRMapper.getHighSchoolTnum();
            List<Integer> highdata = new ArrayList<>();
            List<Integer> middleData = new ArrayList<>();
            List<Integer> primaryData = new ArrayList<>();
            for (Map<String, Object> data : rawData) {
                primaryData.add((Integer) data.get("Tnum"));
            }
            for (Map<String, Object> data : rawData2) {
                middleData.add((Integer) data.get("Tnum"));
            }
            for (Map<String, Object> data : rawData3) {
                highdata.add((Integer) data.get("Tnum"));
            }

            numdong result = new numdong();

            result.setPrimary(primaryData);
            result.setMiddle(middleData);
            result.setHigh(highdata);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new numdong();
        }
    }
}