package com.syt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.syt.entity.SysMenu;
import com.syt.entity.SysUser;
import com.syt.entity.SysUserExample;
import com.syt.mapper.SysUserMapper;
import com.syt.service.SysUsersService;
import com.syt.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service(value = "sysUsersServiceImpl")
public class SysUsersServiceImpl implements SysUsersService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.selectByExample(null);
    }

    @Override
    public R login(SysUser sysUser) {

        SysUserExample example = new SysUserExample();

        example.createCriteria().andUsernameEqualTo(sysUser.getUsername());

        List<SysUser> list = sysUserMapper.selectByExample(example);

        if(list == null || list.size() == 0){
            return R.error("账号不存在");
        }
        SysUser u = list.get(0);

        if(!u.getPassword().equals(sysUser.getPassword())){
            return R.error("密码错误");
        }
        if(u.getStatus() == 0){
            return R.error("账号被冻结");
        }

        return R.ok().put("u",u);
    }

//    @Override
//    public R findUserMenu() {
//        List<SysMenu> list = sysUserMapper.findDir();
//        return null;
//    }
    @RequiresPermissions("sys:menu:select")
    @Override
    public ResultData findByPage(AboutPage aboutPage, String search, AboutSort aboutSort) {
        PageHelper.offsetPage(aboutPage.getOffset(),aboutPage.getLimit());
        SysUserExample example = new SysUserExample();

        if(aboutSort != null && StringUtils.isNotEmpty(aboutSort.getSort())){
            example.setOrderByClause("user_id"+aboutSort.getOrder());
        }

        SysUserExample.Criteria criteria = example.createCriteria();
        if(search != null && !"".equals(search)){
            criteria.andUsernameLike("%"+search+"%");
        }
        List<SysUser> list = sysUserMapper.selectByExample(example);

        PageInfo info = new PageInfo(list);

        ResultData data = new ResultData(info.getTotal(),info.getList());

        return data;
    }
    @RequiresPermissions("sys:menu:select")
    @Override
    public SysUser findByUname(String name) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(name);
        List<SysUser> list = sysUserMapper.selectByExample(example);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public R findPieData() {
        List<Map<String,Object>> list = sysUserMapper.findPieData();

//        List list1 = new ArrayList();
//        for(Map<String,Integer> map: list){
//            String name = map
//        }

        return R.ok().put("pieData",list);
    }
}
