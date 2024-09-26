package com.karry.springbootmybatis.exception;

public class ServiceException extends RuntimeException {//继承RuntimeException
    //自定义一个新的异常
    public ServiceException(String msg) {
        super(msg);
    }
}
