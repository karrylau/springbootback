package com.karry.springbootmybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class result {  // 1

    private Integer code;  // 2
    private String msg;  // 3
    private Object data;  // 4

    public static result success(){  // 5
        return new result(1,"success",null);  // 6
    }

    public static result success(Object data){  // 7
        return new result(1,"success",data);  // 8
    }

    public static result error(String msg){  // 9
        return new result(0,msg,null);  // 10
    }

}
