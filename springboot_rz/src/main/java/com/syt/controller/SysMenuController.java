package com.syt.controller;

import com.syt.entity.SysMenu;
import com.syt.log.Mylog;
import com.syt.service.SysMenuService;
import com.syt.utils.R;
import com.syt.utils.ResultData;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SysMenuController {

    @Resource
    private SysMenuService sysMenuService;
    @Mylog(value = "查询菜单信息",description = "分页查询并且按照名称查询菜单列表")
    @RequiresPermissions("sys:menu:list")
    @RequestMapping("/sys/menu/list")
    public ResultData menuList(int limit,int offset,String search,
                               String sort,String order){

        return sysMenuService.findByPage(limit,offset,search,sort,order);
    }

//    @RequestMapping("/sys/menu/del")
//    public R del(RequestBody List<Long> ids){
//        return sysMenuService.del(ids);
//    }
    @Mylog(value = "查询菜单和目录",description = "查询菜单和名称")
    @RequiresPermissions("sys:menu:select")
    @RequestMapping("/sys/menu/select")
    public R selectMenu(){
        return sysMenuService.selectMenu();
    }

    @Mylog(value = "新增菜单,目录,按钮",description = "新增菜单,目录,按钮")
    @RequiresPermissions("sys:menu:save")
    @RequestMapping("/sys/menu/save")
    public R save(@RequestBody SysMenu sysMenu){
        return sysMenuService.save(sysMenu);
    }

    @Mylog(value = "查询菜单",description = "查询菜单")
    @RequiresPermissions("sys:menu:select")
    @RequestMapping("/sys/menu/info/{menuId}")
    public R findMenu(@PathVariable long menuId){
        return sysMenuService.findMenu(menuId);
    }

    @Mylog(value = "修改菜单",description = "根据菜单编号修改菜单")
    @RequiresPermissions("sys:menu:update")
    @RequestMapping("/sys/menu/update")
    public R update(@RequestBody SysMenu sysMenu){
        return sysMenuService.update(sysMenu);
    }

//    @RequestMapping("/sys/menu/user")
//    public R MenuUser(){
//
//    }
//查询当前用户访问的菜单
    @Mylog(value = "查询用户能访问的菜单",description = "根据菜单编号查询用户能访问的菜单等信息")
    @RequestMapping("/sys/menu/user")
    public R menuUser(){

        return sysMenuService.findUserMenu();
    }


}
