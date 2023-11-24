package com.fyt.controller;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


//@RestControllerAdvice //异常处理类 需要让Mvc配置类扫描到
public class ProjectExceptionAdvice {

    @ExceptionHandler(Exception.class) //处理所有类型异常
    @ResponseBody
    public result doException(Exception ex){
        System.out.println("捕获异常");
        return new result(null,900,"出现异常");
    }
}
