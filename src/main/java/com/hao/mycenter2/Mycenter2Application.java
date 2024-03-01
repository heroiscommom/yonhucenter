package com.hao.mycenter2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hao.mycenter2.mapper")
public class Mycenter2Application {

    public static void main(String[] args) {
        SpringApplication.run(Mycenter2Application.class, args);
    }

}
