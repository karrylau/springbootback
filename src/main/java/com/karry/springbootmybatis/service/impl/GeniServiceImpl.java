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
    public geniCalResult GeniCalculate(Genicalculate genicalculate) {     //根据前端数据计算基尼系数
        geniCalResult result = new geniCalResult();
        Genipara para1 = genicalculate.getShiye();
        Genipara para2 = genicalculate.getGongyong();
        try {
            //for (int year = genicalculate.getStartyear(); year <= genicalculate.getEndyear(); year++) {
                //yearsList.add(year);                  //计算多年基尼系数所需循环
            //yearsList.add(genicalculate.getStartyear());//只计算一年的基尼系数
                int allpeople1 = para1.getPsum();
                double allmoney1 = para1.getMsum();
                Integer[] snum1 = para1.getPnum();
                Double[] cost1 = para1.getMnum();
                double geni1 = 0;
                double pi1 = 0;
                double wi1 = 0;
                double qi1 = 0;
                System.out.println(snum1.length);
                for (int i = 0; i < snum1.length/2; i++) {
                    pi1 = ((double) snum1[i] / allpeople1);
                    wi1 = ((double) cost1[i]/ allmoney1);
                    qi1 += wi1;
                    double gi1 = pi1 * (2 * qi1 - wi1);
                    geni1 += gi1;
                    //System.out.println("pi: " + pi + " wi: " + wi+ " qi: " + qi + " gi: " + gi+ " geni: " + geni);
                }//计算基尼系数
            result.setShiye(1 - geni1);

            int allpeople2 = para2.getPsum();
            double allmoney2 = para2.getMsum();
            Integer[] snum2 = para2.getPnum();
            Double[] cost2 = para2.getMnum();
            double geni2 = 0;
            double pi2 = 0;
            double wi2 = 0;
            double qi2 = 0;
            for (int i = 0; i < snum2.length/2; i++) {
                pi2 = ((double) snum2[i] / allpeople2);
                wi2 = ((double) cost2[i]/ allmoney2);
                qi2 += wi2;
                double gi2 = pi2 * (2 * qi2 - wi2);
                geni2 += gi2;
                System.out.println(i);
                //System.out.println("pi: " + pi + " wi: " + wi+ " qi: " + qi + " gi: " + gi+ " geni: " + geni);
            }//计算基尼系数

            result.setGongyong(1 - geni2);
            System.out.println(result);
            return result;
        }catch (Exception e) {
            e.printStackTrace();
            return new geniCalResult();
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