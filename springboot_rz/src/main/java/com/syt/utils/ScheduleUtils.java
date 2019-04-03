package com.syt.utils;

import com.alibaba.fastjson.JSON;
import com.syt.entity.ScheduleJob;
import com.syt.exception.RZEexception;
import com.syt.quartz.MyJob;
import org.quartz.*;

public class ScheduleUtils {

    public static void createScheduleTask(Scheduler scheduler, ScheduleJob scheduleJob){
        try{
            //1ScheduleFactoryBean--->>>Scheduler对象
            //2，JobDetail
            //在mapper.xml文件中insert语句:useGeneratedKeys="true" keyColumn="job_id" keyProperty="jobId"
            JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity(SysConstant.JOB_KEY_PREFIX+scheduleJob.getJobId()).build();
            //如何向任务类传递参数
            String json = JSON.toJSONString(scheduleJob);
            jobDetail.getJobDataMap().put(SysConstant.SCHEDULE_DATA_KEY,json);
            //3trigger
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(SysConstant.TRIGGER_KEY_PREFIX+scheduleJob.getJobId())
                    .withSchedule(CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())).build();
            scheduler.scheduleJob(jobDetail,cronTrigger);
        }catch(Exception e){
            throw new RZEexception("创建定时任务失败");
        }
    }

    public static void deleteScheduleTask(Scheduler scheduler,long jobId){
        try{
            JobKey jobKey = JobKey.jobKey(SysConstant.JOB_KEY_PREFIX+jobId);
            scheduler.deleteJob(jobKey);
        }catch(Exception e){
            throw new RZEexception("删除定时任务失败");
        }
    }

    public static void updateScheduleTask(Scheduler scheduler,ScheduleJob scheduleJob){
        try{
            //trigger
            TriggerKey triggerKey = TriggerKey.triggerKey(SysConstant.TRIGGER_KEY_PREFIX+scheduleJob.getJobId());
            //获得原来触发器
            CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            //修改触发器的表达式
            CronTrigger newCronTrigger =  cronTrigger.getTriggerBuilder().withSchedule
                    (CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())).build();

            //重置触发器
            scheduler.rescheduleJob(triggerKey,newCronTrigger);
        }catch(Exception e){
            e.printStackTrace();
            throw new RZEexception("修改任务失败，请联系管理员");
        }
    }

    public static void pause(Scheduler scheduler,long jobId){
        try{
            JobKey jobKey = JobKey.jobKey(SysConstant.JOB_KEY_PREFIX+jobId);
            scheduler.pauseJob(jobKey);
        }catch(Exception e){
            throw  new RZEexception("暂停任务失败，请联系管理员");
        }
    }

    public static void resume(Scheduler scheduler,long jobId){
        try{
            JobKey jobKey = JobKey.jobKey(SysConstant.JOB_KEY_PREFIX+jobId);
            scheduler.resumeJob(jobKey);
        }catch(Exception e){
            throw new RZEexception("暂停任务失败,请联系管理员");
        }
    }

    public static void runOnce(Scheduler scheduler,long jobId){
        try{
            System.out.println("￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥");
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+SysConstant.JOB_KEY_PREFIX+jobId);
            JobKey jobKey = JobKey.jobKey(SysConstant.JOB_KEY_PREFIX+jobId);
            scheduler.triggerJob(jobKey);
//            scheduler.start();
        }catch(Exception e){
            throw new RZEexception("执行任务失败，请联系管理员");
        }
    }



}
