package com.karry.springbootmybatis.service.impl;
import com.karry.springbootmybatis.mapper.FinancialRMapper;
import com.karry.springbootmybatis.pojo.*;
import com.karry.springbootmybatis.service.GeniService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.karry.springbootmybatis.mapper.GeniMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
                    allmoney += (Double) GeniData1.get(i).get("EduCost");   //暂时以EduCost为例，后续换成占位符，接收数据加入费用类型即可
                }//计算总人数，总资金
                //System.out.println("allpeople: " + allpeople + " allmoney: " + allmoney);

                for (int i = 0; i < GeniData1.size(); i++) {      //逐步计算基尼系数
                    Integer snum = (Integer) GeniData2.get(i).get("Snum");
                    pi = ((double) snum / allpeople);
                    wi = ((Double) GeniData1.get(i).get("EduCost") / allmoney);
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
    public Geniresult GeniCalculate(Genicalculate genicalculate) {     //根据前端数据计算基尼系数
        Geniresult result = new Geniresult();
        List<Integer> yearsList = new ArrayList<>();
        List<Double> geniList = new ArrayList<>();
        Genipara[] para = genicalculate.getPara();
        int n=0;
        try {
            for (int year = genicalculate.getStartyear(); year <= genicalculate.getEndyear(); year++) {
                yearsList.add(year);
                int allpeople = para[n].getAllpeople();
                double allmoney = para[n].getAllmoney();
                Integer[] snum = para[n].getSnum();
                Double[] cost = para[n++].getCost();
                double geni = 0;
                double pi = 0;
                double wi = 0;
                double qi = 0;
                for (int i = 0; i < snum.length; i++) {
                    pi = ((double) snum[i] / allpeople);
                    wi = ((double) cost[i]/ allmoney);
                    qi += wi;
                    double gi = pi * (2 * qi - wi);
                    geni += gi;
                    //System.out.println("pi: " + pi + " wi: " + wi+ " qi: " + qi + " gi: " + gi+ " geni: " + geni);
                }//计算基尼系数
                geniList.add(1 - geni);
            }
            Integer[] years = yearsList.toArray(new Integer[0]);
            Double[] geni = geniList.toArray(new Double[0]);
            result.setGeni(geni);
            result.setYears(years);
            return result;
        }catch (Exception e) {
            e.printStackTrace();
            return new Geniresult();
        }
    }
}