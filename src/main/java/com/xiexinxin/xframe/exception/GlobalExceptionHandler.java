package com.xiexinxin.xframe.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理类
 * author: xiexx
 * data: 2019/11/13
 * time: 22:34
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Object bexExceptionHandler(BexException bexException) {
//        bexException.printStackTrace();
        Map map = new HashMap();
        map.put("msg", bexException.getMessage());
        System.out.println(map);
        return map;
    }
}
