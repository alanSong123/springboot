package com.syt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.syt.entity.SysMenu;
import com.syt.entity.SysMenuExample;
import com.syt.mapper.SysMenuMapper;
import com.syt.service.SysMenuService;
import com.syt.utils.R;
import com.syt.utils.ResultData;
import com.syt.utils.ShiroUtils;
import com.syt.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.ls.LSInput;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service(value = "sysMenuServiceImpl")
public class SysMenuServiceImpl implements SysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public ResultData findByPage(int limit, int offset, String search, String sort, String order) {
        PageHelper.offsetPage(offset,limit);
        SysMenuExample example = new SysMenuExample();
        SysMenuExample.Criteria criteria = example.createCriteria();
        if(search != null && !"".equals(search)){
            criteria.andNameLike("%"+search+"%");
        }
        if(sort != null && sort.equals("menuId")){
            sort = "menu_id";
        }
        example.setOrderByClause(sort+" "+order);
        List<SysMenu> list = sysMenuMapper.selectByExample(example);

        PageInfo info = new PageInfo(list);

        long total = info.getTotal();
        List<SysMenu> list1 = info.getList();
        ResultData resultData = new ResultData(total,list1);

        return resultData;
    }

    @Override
    public R del(List<Long> ids) {

        SysMenuExample example = new SysMenuExample();
        SysMenuExample.Criteria criteria = example.createCriteria();
        for(Long id : ids){
            if(id < 31){
                return R.error("系统菜单，不能删除！请核对");
            }
        }
        criteria.andMenuIdIn(ids);

        int i = sysMenuMapper.deleteByExample(example);
        if(i > 0){
            return R.ok();
        }

        return R.error("删除失败");
    }

    @Override
    public R selectMenu() {


        List<SysMenu> list = sysMenuMapper.findMenuNotButton();

        SysMenu sysMenu = new SysMenu();
        sysMenu.setMenuId(0L);
        sysMenu.setParentId(-1L);
        sysMenu.setName("一级目录");
        sysMenu.setOrderNum(0);
        list.add(sysMenu);


        return R.ok().put("menuList",list);
    }

    @Override
    public R save(SysMenu sysMenu) {
        int i = sysMenuMapper.insert(sysMenu);
        return i > 0 ? R.ok():R.error("新增失败");
    }

    @Override
    public R findMenu(long menuId) {
        SysMenu sysMenu = sysMenuMapper.selectByPrimaryKey(menuId);

        return R.ok().put("menu",sysMenu);
    }

    @Override
    public R update(SysMenu sysMenu) {
        int i = sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
        if(i > 0){
            return R.ok();
        }
        return R.error("修改失败");
    }

    @Override
    public List<String> findPermsByUserId(long userId) {
        List<String> list = sysMenuMapper.findPermsByUserId(userId);
        Set set = new HashSet<String>();
        for (String s : list){
            if(StringUtils.isNotEmpty(s)){
                String ss[] = s.split(",");
                for(String s1 : ss){
                    set.add(s1);
                }
            }
        }
        List<String> newList = new ArrayList<>();
        newList.addAll(set);
        return newList;
    }


    @Override
    public R findUserMenu() {

        long userId = ShiroUtils.getUserId();

        List<SysMenu> dir = sysMenuMapper.findDir(userId);

        for (SysMenu sysMenu : dir) {
            //查询目录
            List<SysMenu> menu = sysMenuMapper.findMenu(sysMenu.getMenuId(),userId);

            sysMenu.setList(menu);
        }
        List<String> permissions = this.findPermsByUserId(userId);

        return R.ok().put("menuList",dir).put("permissions",permissions);

    }
}
