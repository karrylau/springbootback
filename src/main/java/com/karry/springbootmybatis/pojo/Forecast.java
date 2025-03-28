package com.karry.springbootmybatis.pojo;

import lombok.Data;

@Data
public class Forecast {
    Integer year;
    String location;
    String stage;
}
