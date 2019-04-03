package com.syt.config;


import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class QuartzConfig {

//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean(){
//        SchedulerF
//    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(@Qualifier(value = "druidDatasource")DataSource dataSource){
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        Properties prop = new Properties();
        prop.setProperty("org.quartz.scheduler.instanceName","MyQuartzScheduler");
        prop.setProperty("org.quartz.scheduler.instanceid","AUTO");
        prop.setProperty("org.quartz.threadPool.class","org.quartz.simpl.SimpleThreadPool");
        prop.setProperty("org.quartz.threadPool.threadCount","10");
        prop.setProperty("org.quartz.jobStore.class","org.quartz.impl.jdbcjobstore.JobStoreTX");
        prop.setProperty(" org.quartz.jobStore.tablePrefix","QRTZ_");
        bean.setQuartzProperties(prop);
        bean.setAutoStartup(true);
        bean.setOverwriteExistingJobs(true);
        bean.setStartupDelay(5);
        bean.setDataSource(dataSource);
        return bean;
    }

}
