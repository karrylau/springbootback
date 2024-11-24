package com.karry.springbootmybatis.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class numdong {
    private List<Integer> primary;
    private List<Integer> middle;
    private List<Integer> high;
}
