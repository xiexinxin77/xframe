package com.xiexinxin.xframe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xiexinxin.xframe.mapper")
public class XframeApplication {

    public static void main(String[] args) {
        SpringApplication.run(XframeApplication.class, args);
    }

}
