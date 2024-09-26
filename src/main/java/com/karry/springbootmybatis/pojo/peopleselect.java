package com.karry.springbootmybatis.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
public class peopleselect {
    private Integer input;
    private Integer input2;
    private String value;
    private String value2;

    public peopleselect() {
    }
}
