package com.xiexinxin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xiexinxin.business.dao")
public class XframeApplication {

    public static void main(String[] args) {
        SpringApplication.run(XframeApplication.class, args);
    }

}
