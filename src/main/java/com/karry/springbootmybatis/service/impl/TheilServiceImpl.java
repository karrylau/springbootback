package com.karry.springbootmybatis.service.impl;

import com.karry.springbootmybatis.mapper.TheilMapper;
import com.karry.springbootmybatis.pojo.TheilResult;
import com.karry.springbootmybatis.service.TheilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TheilServiceImpl implements TheilService {
    private static final Map<String, String> REGION_MAP = new HashMap<>();  //创建一个hash表，但没什么用，给的顺序是一定的，那传来的数据我可以直接用
    static {
        REGION_MAP.put("黑龙江省", "东北地区");
        REGION_MAP.put("吉林省", "东北地区");
        REGION_MAP.put("辽宁省", "东北地区");
        REGION_MAP.put("内蒙古自治区", "东北地区");

        REGION_MAP.put("北京市", "华北地区");
        REGION_MAP.put("天津市", "华北地区");
        REGION_MAP.put("山西省", "华北地区");
        REGION_MAP.put("河北省", "华北地区");

        REGION_MAP.put("上海市", "华东地区");
        REGION_MAP.put("江苏省", "华东地区");
        REGION_MAP.put("浙江省", "华东地区");
        REGION_MAP.put("安徽省", "华东地区");
        REGION_MAP.put("福建省", "华东地区");
        REGION_MAP.put("江西省", "华东地区");
        REGION_MAP.put("山东省", "华东地区");

        REGION_MAP.put("河南省", "中南地区");
        REGION_MAP.put("湖南省", "中南地区");
        REGION_MAP.put("湖北省", "中南地区");
        REGION_MAP.put("广东省", "中南地区");
        REGION_MAP.put("广西壮族自治区", "中南地区");
        REGION_MAP.put("海南省", "中南地区");

        REGION_MAP.put("四川省", "西南地区");
        REGION_MAP.put("重庆市", "西南地区");
        REGION_MAP.put("贵州省", "西南地区");
        REGION_MAP.put("云南省", "西南地区");
        REGION_MAP.put("西藏自治区", "西南地区");

        REGION_MAP.put("陕西省", "西北地区");
        REGION_MAP.put("甘肃省", "西北地区");
        REGION_MAP.put("青海省", "西北地区");
        REGION_MAP.put("宁夏回族自治区", "西北地区");
        REGION_MAP.put("新疆维吾尔自治区", "西北地区");

        REGION_MAP.put("香港", "港澳台地区");
        REGION_MAP.put("澳门", "港澳台地区");
        REGION_MAP.put("台湾省", "港澳台地区");
    } //一个大辞典

    @Autowired
    public TheilMapper theilMapper;

    public Map<String, Object> getFinancialDataWithProvinces(Integer year) {
        // 从数据库获取教育经费数据
        List<Map<String, Double>> financialResList = theilMapper.getAllFinancialData(year);//形成序列表 对应的是省份和数据,这个是有用的
        System.out.println(financialResList);
        //获取人力资源表中的学生数数据
        List<Map<String, Integer>> studentDataList = theilMapper.getStudentData(year);//没有用到学生数
        System.out.println(studentDataList);
        // 创建一个 Map 用于存储省份与教育成本的映射
        Map<String, Double> financialDataMap = new HashMap<>();//province以及教育经费Educost

        // 将数据库中的每一项数据填充到 Map 中，其实这一步多此一举
        for (Map<String, Double>  financialRes : financialResList) {   //创建一个theil financialRes，把list里面的数据放进去
            financialDataMap.put(String.valueOf((financialRes.get("location"))), (financialRes.get("EduCost")));//再将数据放到新建的map,其实一开始将它们放进去就行
        System.out.println(String.valueOf((financialRes.get("location")))+financialRes.get("EduCost"));
        }

        // 创建一个 Map 用于存储省份与学生数的映射
        Map<String, Integer> studentDataMap = new HashMap<>();

        // 将学生数数据填充到 studentDataMap 中
        for (Map<String, Integer> studentData : studentDataList) {  //将学生数据传入新建的map
            String province = String.valueOf(studentData.get("location")); //两种写法，一种是location，也就是mapper决定的
            Integer studentNum =studentData.get("Snum");
            studentDataMap.put(province, studentNum);//最后都是把数值传给map
        }

        // 创建一个 Map 用于存储各省的生均教育经费，计算生均教育经费
        Map<String,Integer> provinceEduCostMap = new HashMap<>();
        // 根据财力资源和学生数计算生均教育经费
        for (String province : financialDataMap.keySet()) {  //从financial里面把每一个键值找到，对应着键值去找共同值
            Double eduCost = financialDataMap.get(province);//一种是province，由theil的pojo结构体决定
            Integer studentNum = studentDataMap.get(province);

            if (eduCost != null && studentNum != null && studentNum > 0) {
                Integer perStudentEduCost = (int) (eduCost / studentNum);
                provinceEduCostMap.put(province, perStudentEduCost);//将计算值放进去，这个是真牛，没的说
            }
        }
/*
        //继续写区域内外泰尔指数
        double alleducost = 0; //所有学生费用
        int alledunum = 0;  //所有学生人数

        // 用来存储各省份的教育经费和学生数
        Map<String, Double> provinceEduCostMap2 = new HashMap<>();
        Map<String, Integer> provinceStudentMap = new HashMap<>();

        // 用来存储各地区的教育经费和学生数
        Map<String, Double> regionEduCostMap = new HashMap<>(); //其实就是把他们加起来，我觉得可以用一个数组
        Map<String, Integer> regionStudentMap = new HashMap<>();

        // 填充各省份和地区的教育经费及学生数
        for (Theil financialRes : financialResList) {
            String province = financialRes.getLocation();
            double eduCost = financialRes.getEduCost();
            provinceEduCostMap2.put(province, eduCost);
            alleducost += eduCost;

            // 获取该省的学生数
            //Integer studentNum = provinceStudentMap.get(province);//现在人家是空的，弄错了
            //provinceStudentMap.put(province, studentNum == null ? 0 : studentNum);  //通过这样的方式，让数值对应，没问题
        }

        for (Map<String, Object> studentData : studentDataList) {
            String province = (String) studentData.get("province");
            Integer studentNum = (Integer) studentData.get("snum");
            provinceStudentMap.put(province, studentNum);

            // 根据province填充对应的region（区域）
            String region = REGION_MAP.get(province);
            regionEduCostMap.put(region, regionEduCostMap.getOrDefault(region, 0.0) + provinceEduCostMap.get(province));
            regionStudentMap.put(region, regionStudentMap.getOrDefault(region, 0) + studentNum);  //很巧妙地计算了一个值

            alledunum += studentNum;//将所有的数量加在一起
        }

        // 计算各省的泰尔数值，区域内泰尔指数（area）
        double areaTheil = 0;
        for (String province : provinceEduCostMap2.keySet()) {
            double eduCost = provinceEduCostMap2.get(province);
            int studentNum = provinceStudentMap.get(province);
            String region = REGION_MAP.get(province); //真尼玛天才
            double regionEduCost = regionEduCostMap.get(region);  //区域的名称也不是值？
            int regionStudentNum = regionStudentMap.get(region);

            // 计算省份的泰尔数值
            double pEduCost = eduCost / alleducost;  //计算省份与总教育经费相除
            double rEduCost = regionEduCost / alleducost;//计算区域与总教育经费比值
            double pStudentNum = studentNum / (double) alledunum;//计算省份学生人数与总学生人数
            double rStudentNum = regionStudentNum / (double) alledunum;//计算区域人数与总学生人数

            if (pEduCost > 0 && rEduCost > 0 && pStudentNum > 0 && rStudentNum > 0) {
                areaTheil += pEduCost * Math.log(pEduCost / rEduCost * rStudentNum / pStudentNum);
            }
        }

        // 计算各地区的泰尔数值，区域间泰尔指数（region）
        double regionTheil = 0;
        for (String region : regionEduCostMap.keySet()) {
            double regionEduCost = regionEduCostMap.get(region);
            int regionStudentNum = regionStudentMap.get(region);

            double pEduCost = regionEduCost / alleducost;
            double pStudentNum = regionStudentNum / (double) alledunum;

            if (pEduCost > 0 && pStudentNum > 0) {
                regionTheil += pEduCost * Math.log(pEduCost / (alleducost / alleducost) * pStudentNum / (alledunum / alledunum));
            }
        }


*/
        // 创建一个包含表头的外层 Map
        Map<String, Object> result = new HashMap<>();//创建一个result.result里面包含了，map里面有关键的数值，关键的键
        result.put("shiye", provinceEduCostMap);
        //result.put("area", areaTheil);
        //result.put("region", regionTheil);
        return result;
    }
    // 区域与省份的映射
    //计算的是区域间泰尔指数和区域内泰尔指数
    public TheilResult getarea(Map<String,Integer> data)  //那么问题来了，我的人数怎么去定呢？就用之前的人数。
   {
       Double theil=0.0;
       Double area=0.0;
       Double region=0.0;
       double alleducost = 0; //所有学生费用
       int alledunum = 0;  //所有学生人数
       Integer year=2016;//后面可以根据year的值进行修改

       //System.out.println(data.get("江苏省")); //data没问题，但是是乱序的,data代表的是
       List<Map<String,Integer>> stunum = theilMapper.getStudentData(year);//隐含了一个问题，第二次传值的时候没有年份，就会有很多问题，先暂定是这个人数
       Map<String, Integer> provinceStudentMap = new HashMap<>();  //每个省份的学生数
       Map<String, Double> regionEduCostMap = new HashMap<>(); //每个地区的教育经费综合
       Map<String, Integer> regionStudentMap = new HashMap<>();//每个地区的学生数总和

       //获取总教育经费
       for(String province:data.keySet())
       {
           alleducost =data.get(province)+alleducost; //get(province)是一个具体数据
       }

       //获取每个省份的学生数,并且算好每个区域的总值
       for(Map<String,Integer>stu:stunum)
       {
           String province = String.valueOf(stu.get("location"));  //这个是“”代表的是名称。province代表的是选项
           Integer studentnum = stu.get("Snum");
           provinceStudentMap.put(province,studentnum);
           //System.out.println((provinceStudentMap));验证了，没问题
           //根据province填充对应的region区域
           String region1 = REGION_MAP.get(province); //得到所属的区域
           regionEduCostMap.merge(region1, Double.valueOf(data.get(province)), Double::sum);//每个区域的教育经费总和
           //System.out.println( regionEduCostMap);验证了没问题
           regionStudentMap.merge(region1,studentnum, Integer::sum); //每个区域的人数总和
           alledunum += studentnum;
       }

       //计算各省的泰尔指数，区域内泰尔指数
       for(String province:data.keySet())//获取每一个省份
       {
           Double educost = Double.valueOf(data.get(province));
           Integer stunumber =  provinceStudentMap.get(province);
           //System.out.println(stunumber);证明没有问题
           String regiono= REGION_MAP.get(province);  //获取该省份的所属的地区，然后再在对应的区域操作
           Double regionEduCost = regionEduCostMap.get(regiono); //获取该地区总的经费
           Integer regionStudentNum = regionStudentMap.get(regiono);
           //System.out.println(educost/alleducost);
           //System.out.println(educost/regionEduCost);
           //System.out.println(stunumber/regionStudentNum);
           //System.out.println(stunumber);
           //System.out.println(regionStudentNum);
           Double pstudentnum = (double) stunumber /regionStudentNum;//最后发现int/int
           //计算省份的泰尔指数
           area += (educost/alleducost)*Math.log((educost/regionEduCost)/pstudentnum);
           System.out.println(area);
       }

       //计算各地区的泰尔指数，区域间泰尔指数
       for(String regiont : regionEduCostMap.keySet())
       {
           Double regionEduCost= regionEduCostMap.get(regiont); //获取对应地区的总教育经费
           Integer regionStudentNum = regionStudentMap.get(regiont);//获取对应地区的总人数

           Double pstudentnumt = (double) regionStudentNum/alledunum;
           //计算各地区的泰尔指数，然后相加
           region += (regionEduCost/alleducost)*Math.log((regionEduCost/alleducost)/pstudentnumt);
           System.out.println(region);
       }





       //Map<Double,Double> difregion= new HashMap<>();//得到一个list,里面放的是每一个地区的总经费和人口数
       //Map<Double,Double> again
       TheilResult result = new TheilResult();
       theil=area+region;
       result.setTheil(theil);
       result.setArea(area);
       result.setRegion(region);
       //result.put("theil",theil);
       //result.put("area", area);
       //result.put("region", region);
       return result;
   }
}

