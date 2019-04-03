package com.syt.service;

import com.syt.entity.SysUser;
import com.syt.utils.AboutPage;
import com.syt.utils.AboutSort;
import com.syt.utils.R;
import com.syt.utils.ResultData;

import java.util.List;

public interface SysUsersService {

    public List<SysUser> findAll();

    public R login(SysUser sysUser);

    public SysUser findByUname(String name);

    public ResultData findByPage(AboutPage aboutPage,
                                 String search,
                                 AboutSort aboutSort);

//    public R findUserMenu();


    R findPieData();

}
