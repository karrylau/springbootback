package com.karry.springbootmybatis.pojo;
import lombok.Data;
import java.util.Map;

@Data
public class TheilRequest
{
        private Map<String, Integer> tuningData;
        private String location;
}
