package com.karry.springbootmybatis.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
public class Geniselect {
    private peopleselect peopleselect;
    private timeselect timeselect;

    public Geniselect() {
    }
}