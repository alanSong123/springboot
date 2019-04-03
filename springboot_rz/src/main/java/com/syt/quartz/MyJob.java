package com.syt.quartz;

import com.alibaba.fastjson.JSON;
import com.sun.swing.internal.plaf.synth.resources.synth_sv;
import com.syt.entity.ScheduleJob;
import com.syt.quartz.task.BackDBTask;
import com.syt.quartz.task.UnLockAccount;
import com.syt.utils.SpringContextUtils;
import com.syt.utils.SysConstant;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.applet.AppletContext;
import java.lang.reflect.Method;

public class MyJob implements Job {

    @Override
    public void execute(JobExecutionContext context){
//        System.out.println("helloworld!!!");
//        BackDBTask task = new BackDBTask();
//        task.backUp();
//        UnLockAccount account = new UnLockAccount();
//        account.unLock();


        try{
            String json = (String) context.getJobDetail().getJobDataMap().get(SysConstant.SCHEDULE_DATA_KEY);
            System.out.println(json);
//        System.out.println(json);
            ScheduleJob scheduleJob = JSON.parseObject(json,ScheduleJob.class);
            String beanName = scheduleJob.getBeanName();
            String method = scheduleJob.getMethodName();
            System.out.println("--------------------------------------------------------------------");
            System.out.println(beanName+"+++++++++++++++++++++++++++++++++++++++++++++++++++"+method);
            System.out.println("--------------------------------------------------------------------");
//        AppletContext context1 = new ClassPathXmlApplicationContext("spring.xml");
            Object o = SpringContextUtils.getBean(beanName);
            Class clazz = o.getClass();
            Method method1 = clazz.getDeclaredMethod(method);
            method1.invoke(o);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
