package com.syt.service.impl;

import com.syt.entity.SysLog;
import com.syt.mapper.SysLogMapper;
import com.syt.service.SysLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class SysLogServiceImpl implements SysLogService {

    @Resource
    private SysLogMapper sysLogMapper;

    @Override
    public int saveLog(SysLog sysLog) {
        return sysLogMapper.insert(sysLog);
    }
}
