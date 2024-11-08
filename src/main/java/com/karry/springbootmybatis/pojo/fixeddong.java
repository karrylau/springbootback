package com.karry.springbootmybatis.pojo;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class fixeddong {
    private List<Double> primary;
    private List<Double> middle;
    private List<Double> high;
}
