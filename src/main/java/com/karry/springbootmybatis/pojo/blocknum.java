package com.karry.springbootmybatis.pojo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class blocknum {
    private block primary;
    private block middle;
    private block senior;
}
