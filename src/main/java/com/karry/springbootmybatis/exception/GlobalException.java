package com.karry.springbootmybatis.exception;

import com.karry.springbootmybatis.pojo.result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(ServiceException.class)//指定捕获的异常类型
    @ResponseBody//将java对象转为json格式的数据
public result serviceExcept(ServiceException e) {//捕获到的异常对象

    return result.error(500,e.getMessage());
}
}

