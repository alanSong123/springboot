package com.syt.service;

import com.syt.entity.ScheduleJob;
import com.syt.utils.AboutPage;
import com.syt.utils.R;
import com.syt.utils.ResultData;

import java.util.List;

public interface ScheduleService {

    public ResultData scheduleList(AboutPage page, String search);

    public R save(ScheduleJob scheduleJob);
    public R update(ScheduleJob scheduleJob);
    public R delete(List<Long> jobIds);
    //暂停
    public R pause(List<Long> jobIds);
    //恢复
    public R resume(List<Long> jobIds);
    //执行一次
    public R run(List<Long> jobIds);

    public R scheduleInfo(long id);


}
