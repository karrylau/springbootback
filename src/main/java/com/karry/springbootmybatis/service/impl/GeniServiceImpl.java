package com.karry.springbootmybatis.service.impl;
import com.karry.springbootmybatis.mapper.FinancialRMapper;
import com.karry.springbootmybatis.pojo.*;
import com.karry.springbootmybatis.service.GeniService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.karry.springbootmybatis.mapper.GeniMapper;

import java.util.*;

@Service
public class GeniServiceImpl implements GeniService {
    @Autowired
    private GeniMapper GeniMapper;

    @Override
    public Geniresult getGeni() {
        return null;
    } //空



    @Override
    public Geniresult getGeni(Geniselect geniselect) {         //根据年份寻找表中数据计算基尼系数
        String stage = geniselect.getPeopleselect().getValue();
        List<Integer> yearsList = new ArrayList<>();
        List<Double> geniList = new ArrayList<>();
        try {                                                 //根据起止年份逐年计算基尼系数
            for (int year = geniselect.getTimeselect().getStartYear(); year <= geniselect.getTimeselect().getEndYear(); year++) {
                yearsList.add(year);//压入年份，以便与同年份数据对齐
                List<Map<String, Object>> GeniData1 = GeniMapper.getGeni1(year, stage);
                List<Map<String, Object>> GeniData2 = GeniMapper.getGeni2(year, stage);
                double geni = 0;
                double pi = 0;
                double wi = 0;
                double qi = 0;
                int allpeople = 0;
                double allmoney = 0;

                if (GeniData1.size() != GeniData2.size()) {       //大小不一说明读取数据库数据错误，直接报错即可
                    throw new IllegalStateException("error");
                }
               //System.out.println("GeniData1: " + GeniData1.size() + " GeniData2: " + GeniData2.size());

                for (int i = 0; i < GeniData1.size(); i++) {      //分别获取两库数据
                    allpeople += (Integer) GeniData2.get(i).get("Snum");
                    allmoney += (Double) GeniData1.get(i).get(geniselect.getMoneyselect());   //暂时以EduCost为例，后续换成占位符，接收数据加入费用类型即可
                }//计算总人数，总资金
                //System.out.println("allpeople: " + allpeople + " allmoney: " + allmoney);

                for (int i = 0; i < GeniData1.size(); i++) {      //逐步计算基尼系数
                    Integer snum = (Integer) GeniData2.get(i).get("Snum");
                    pi = ((double) snum / allpeople);
                    wi = ((double) GeniData1.get(i).get(geniselect.getMoneyselect()) / allmoney);
                    qi += wi;
                    double gi = pi * (2 * qi - wi);
                    geni += gi;
                    //System.out.println("pi: " + pi + " wi: " + wi+ " qi: " + qi + " gi: " + gi+ " geni: " + geni);
                }//计算基尼系数
                geniList.add(1 - geni);
            }

            Integer[] years = yearsList.toArray(new Integer[0]);
            Double[] geni = geniList.toArray(new Double[0]);
            Geniresult result = new Geniresult();
            result.setGeni(geni);
            result.setYears(years);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Geniresult();
        }
    }



    @Override
    public geniCalResult GeniCalculate(Genicalculate genicalculate) {
        geniCalResult result = new geniCalResult();
        Genipara para1 = genicalculate.getShiye();
        Genipara para2 = genicalculate.getGongyong();

        try {
            // 计算第一个基尼系数(shiye)
            if (para1 != null) {
                result.setShiye(calculateGini(para1));
            }

            // 计算第二个基尼系数(gongyong)
            if (para2 != null) {
                result.setGongyong(calculateGini(para2));
            }

            //System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new geniCalResult();
        }
    }

    private double calculateGini(Genipara para) {
        if (para == null || para.getPnum() == null || para.getMnum() == null) {
            return 0.0;
        }

        Integer[] snum = para.getPnum();
        Double[] cost = para.getMnum();
        int allpeople = para.getPsum();
        double allmoney = para.getMsum();

        // 验证数据
        if (snum.length != cost.length || allpeople <= 0 || allmoney <= 0.0) {
            return 0.0;
        }

        // 将数据按收入/支出从小到大排序
        List<IncomeGroup> groups = new ArrayList<>();
        for (int i = 0; i < snum.length; i++) {
            if (snum[i] != null && cost[i] != null) {
                groups.add(new IncomeGroup(snum[i], cost[i]));
            }
        }

        // 按人均收入排序
        Collections.sort(groups, Comparator.comparingDouble(g -> g.costPerCapita()));

        // 计算基尼系数
        double geni = 0.0;
        double cumulativeProp = 0.0; // 累计人口比例
        double cumulativeIncomeProp = 0.0; // 累计收入比例

        for (IncomeGroup group : groups) {
            double prop = (double) group.people / allpeople;
            double incomeProp = group.income / allmoney;

            geni += prop * (2 * cumulativeIncomeProp + incomeProp);
            cumulativeProp += prop;
            cumulativeIncomeProp += incomeProp;
        }

        return 1 - geni;
    }

    // 辅助类，用于排序和计算
    private static class IncomeGroup {
        int people;
        double income;

        IncomeGroup(int people, double income) {
            this.people = people;
            this.income = income;
        }

        double costPerCapita() {
            return people == 0 ? 0 : income / people;
        }
    }



    @Override
    public Map<String, Searchresult> SearchGeni(Integer year) {
        // 1. 从数据库获取原始数据
        List<Map<String, Object>> GeniData1 = GeniMapper.searchmoney(year);
        List<Map<String, Object>> GeniData2 = GeniMapper.searchnum(year);

        // 2. 使用 LinkedHashMap 保持插入顺序
        Map<String, Searchresult> resultMap = new LinkedHashMap<>();

        // 3. 处理 GeniData1：计算每个地区的累计费用
        Map<String, Double[]> locationTotals = new HashMap<>();
        for (Map<String, Object> data : GeniData1) {
            String location = (String) data.get("location");
            Double culCost = (Double) data.get("CulCost");
            Double pubCost = (Double) data.get("PubCost");

            locationTotals.putIfAbsent(location, new Double[]{0.0, 0.0});
            Double[] totals = locationTotals.get(location);
            totals[0] += culCost;  // 累计 CulCost
            totals[1] += pubCost;  // 累计 PubCost
        }

        // 4. 将累计数据转换为 Searchresult 对象，并按地区名排序
        List<Map<String, Object>> sortedData = new ArrayList<>();
        for (Map.Entry<String, Double[]> entry : locationTotals.entrySet()) {
            Map<String, Object> map = new HashMap<>();
            map.put("location", entry.getKey());
            map.put("CulCost", entry.getValue()[0]);
            map.put("PubCost", entry.getValue()[1]);
            sortedData.add(map);
        }

        // 按地区名排序
        sortedData.sort(Comparator.comparing(map -> (String) map.get("location")));

        // 5. 填充 resultMap
        for (Map<String, Object> data : sortedData) {
            String location = (String) data.get("location");
            Searchresult sr = new Searchresult();
            sr.setCulcost((Double) data.get("CulCost"));
            sr.setPubcost((Double) data.get("PubCost"));
            sr.setStu(0);  // 初始学生数为0
            resultMap.put(location, sr);
        }

        // 6. 处理 GeniData2：更新学生数量
        for (Map<String, Object> data : GeniData2) {
            String location = (String) data.get("location");
            Integer studentNum = (Integer) data.get("Snum");

            if (resultMap.containsKey(location)) {
                Searchresult sr = resultMap.get(location);
                sr.setStu(sr.getStu() + studentNum);  // 累加学生数
            } else {
                // 可选：处理未匹配的地区（根据业务需求）
                // Searchresult newSr = new Searchresult(0.0, 0.0, studentNum);
                // resultMap.put(location, newSr);
            }
        }

        return resultMap;
    }
}