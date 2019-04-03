package com.syt.controller;

import com.syt.service.SysUsersService;
import com.syt.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
@RestController
public class EchartsController {

    @Resource
    private SysUsersService sysUsersService;


    @RequestMapping("/sys/user/pie")
    public R pie(){
        return sysUsersService.findPieData();
    }

}
