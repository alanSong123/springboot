package com.syt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.syt.config") //扫描servlet相关的注解
@EnableTransactionManagement
@MapperScan(basePackages = "com.syt.mapper")
public class SpringbootRzApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRzApplication.class, args);
    }

}
