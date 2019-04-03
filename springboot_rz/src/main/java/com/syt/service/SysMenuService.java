package com.syt.service;

import com.syt.entity.SysMenu;
import com.syt.utils.R;
import com.syt.utils.ResultData;

import java.util.List;

public interface SysMenuService {

    public ResultData findByPage(int limit,int offset,String search,
                                 String sort,String order);

    public R del(List<Long> ids);

    public R selectMenu();

    public R save(SysMenu sysMenu);

    public R findMenu(long menuId);

    public R update(SysMenu sysMenu);

    public List<String> findPermsByUserId(long userId);

    public R findUserMenu();

}
