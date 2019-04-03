package com.syt.utils;

import com.syt.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import javax.security.auth.Subject;

public class ShiroUtils {

    public static Session getSession(){
        return SecurityUtils.getSubject().getSession();
    }

    public static void setAttribute(String k,String v){
        getSession().setAttribute(k,v);
    }

    public static  Object getAttribute(String k){
        return getSession().getAttribute(k);
    }

    public static String getCaptcha(){
        String str = getAttribute("code")+"";
        return str;
    }

    public static SysUser getCurrentUser(){
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    public static void logout(){
        SecurityUtils.getSubject().logout();
    }

    public static long getUserId(){
        return getCurrentUser().getUserId();
    }

}
