package com.syt.controller;


import com.syt.utils.R;
import com.syt.utils.ShiroUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LogoutController {

//    @RequestMapping("/logout")
//    public String logout(){
//        //清空session
//        ShiroUtils.logout();
//
//        return "redirect:/login.html";
//    }

    @RequestMapping("/logout")
    @ResponseBody
    public R logout(){
        //清空session
        ShiroUtils.logout();
        return  R.ok();
    }

}
