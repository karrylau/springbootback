package com.karry.springbootmybatis.pojo;
import lombok.Data;
import java.util.List;

@Data
public class map {
    List<feature> schools;
    List<feature> teachernum;
    List<feature> studentnum;
    List<feature> fixedassets;
}
