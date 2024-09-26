//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.karry.springbootmybatis.exception;

import com.karry.springbootmybatis.pojo.result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {
    public GlobalException() {
    }

    @ExceptionHandler({ServiceException.class})
    @ResponseBody
    public result serviceExcept(ServiceException e) {
        return result.error(500, e.getMessage());
    }
}
