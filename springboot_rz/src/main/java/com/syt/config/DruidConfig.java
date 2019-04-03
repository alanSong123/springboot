package com.syt.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DruidConfig {
    
    @Bean(name = "druidDatasource")
    //加载配置文件中开头的配置
    @ConfigurationProperties(value = "spring.datasource")
    public DataSource dataSource(){
        System.out.println("------->>>datasource");
        DruidDataSource dataSource = new DruidDataSource();

        
        
        return dataSource;
    }


    
}
