package com.syt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.syt.entity.ScheduleJob;
import com.syt.entity.ScheduleJobExample;
import com.syt.mapper.ScheduleJobMapper;
import com.syt.service.ScheduleService;
import com.syt.utils.*;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Resource
    private ScheduleJobMapper scheduleJobMapper;

    //依赖Scheduler 在QuartzConfig定义
    @Resource
    private Scheduler scheduler;

    @Override
    public ResultData scheduleList(AboutPage page, String search) {

        PageHelper.offsetPage(page.getOffset(),page.getLimit());

        ScheduleJobExample example  = new ScheduleJobExample();

        if(StringUtils.isNotEmpty(search)){
            ScheduleJobExample.Criteria criteria = example.createCriteria();
            criteria.andBeanNameLike("%"+search+"%");
        }

        List<ScheduleJob> list = scheduleJobMapper.selectByExample(example);

        PageInfo pageInfo = new PageInfo(list);

        ResultData resultData = new ResultData(pageInfo.getTotal(),pageInfo.getList());

        return resultData;
    }

    @Override
    public R save(ScheduleJob scheduleJob) {

        scheduleJob.setStatus(SysConstant.ScheduleStates.NOMAL.getValue());
        scheduleJob.setCreateTime(new Date());

        //1保存Schedule_job表
        int i = scheduleJobMapper.insert(scheduleJob);


        //2真正定时任务的创建
        ScheduleUtils.createScheduleTask(scheduler,scheduleJob);

        return i>0?R.ok():R.error();
    }

    @Override
    public R update(ScheduleJob scheduleJob) {
        int i = scheduleJobMapper.updateByPrimaryKeySelective(scheduleJob);

        ScheduleUtils.updateScheduleTask(scheduler,scheduleJob);


        return i>0?R.ok():R.error();
    }

    @Override
    public R delete(List<Long> jobIds) {
        //1删除Schedule_job表的记录
        ScheduleJobExample example = new ScheduleJobExample();
        ScheduleJobExample.Criteria criteria = example.createCriteria();
        criteria.andJobIdIn(jobIds);
        int i = scheduleJobMapper.deleteByExample(example);

        //删除真正的定时任务
        for(Long jobId : jobIds){
            ScheduleUtils.deleteScheduleTask(scheduler,jobId);
        }


        return i>0?R.ok():R.error();
    }

    @Override
    public R pause(List<Long> jobIds) {
        //修改数据库Schedule_job表的状态 status
        ScheduleJobExample example = new ScheduleJobExample();
        ScheduleJobExample.Criteria criteria = example.createCriteria();
        criteria.andJobIdIn(jobIds);
        ScheduleJob scheduleJob = new ScheduleJob();
        scheduleJob.setStatus(SysConstant.ScheduleStates.PAUSE.getValue());
        int i = scheduleJobMapper.updateByExampleSelective(scheduleJob,example);

        for(Long jobId : jobIds){
            ScheduleUtils.pause(scheduler,jobId);
        }


        return i>0?R.ok():R.error();
    }

    @Override
    public R resume(List<Long> jobIds) {

        ScheduleJobExample example = new ScheduleJobExample();
        ScheduleJobExample.Criteria criteria = example.createCriteria();
        criteria.andJobIdIn(jobIds);
        ScheduleJob scheduleJob = new ScheduleJob();
        scheduleJob.setStatus(SysConstant.ScheduleStates.NOMAL.getValue());
        int i = scheduleJobMapper.updateByExampleSelective(scheduleJob,example);
        for(Long jobId : jobIds){
            ScheduleUtils.resume(scheduler,jobId);
        }

        return i>0?R.ok():R.error();
    }

    @Override
    public R run(List<Long> jobIds) {
        System.out.println(jobIds.size()+"SIZESIZESIZESIZESIZESIZESIZESIZESIZESIZE");
        for(Long jobId : jobIds){
            ScheduleUtils.runOnce(scheduler,jobId);
        }
        return R.ok();
    }

    @Override
    public R scheduleInfo(long id) {
        ScheduleJob scheduleJob = scheduleJobMapper.selectByPrimaryKey(id);

        return R.ok().put("scheduleJob",scheduleJob);
    }
}
