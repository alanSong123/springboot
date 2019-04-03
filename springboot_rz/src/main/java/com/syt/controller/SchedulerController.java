package com.syt.controller;

import com.syt.entity.ScheduleJob;
import com.syt.service.ScheduleService;
import com.syt.utils.AboutPage;
import com.syt.utils.R;
import com.syt.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchedulerController {

    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping("/schedule/job/list")
    public ResultData scheduList(AboutPage page,String search){
        return scheduleService.scheduleList(page,search);
    }

    @RequestMapping("/schedule/job/info/{jobId}")
    public R info(@PathVariable long jobId){
        return scheduleService.scheduleInfo(jobId);
    }

    @RequestMapping("/schedule/job/save")
    public R save(@RequestBody ScheduleJob scheduleJob)
    {

        return scheduleService.save(scheduleJob);
    }

    @RequestMapping("/schedule/job/update")
    public R update(@RequestBody ScheduleJob scheduleJob){
        return scheduleService.update(scheduleJob);
    }

    @RequestMapping("/schedule/job/del")
    public R delete(@RequestBody List<Long> jobIds){
        return scheduleService.delete(jobIds);
    }

    @RequestMapping("/schedule/job/resume")
    public R resume(@RequestBody List<Long> jobIds){
        return scheduleService.resume(jobIds);
    }

    @RequestMapping("/schedule/job/run")
    public R run(@RequestBody List<Long> jobIds){
        return scheduleService.run(jobIds);
    }

    @RequestMapping("/schedule/job/pause")
    public R pause(@RequestBody List<Long> jobIds){
        return scheduleService.pause(jobIds);
    }
}
