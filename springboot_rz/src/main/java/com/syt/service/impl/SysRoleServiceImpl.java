package com.syt.service.impl;

import com.syt.mapper.SysRoleMapper;
import com.syt.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("sysRoleServiceImpl")
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<String> findRolesByUserId(long userId) {

        return sysRoleMapper.findRolesByUserId(userId);
    }
}
