package com.syt.dto;

import com.syt.entity.SysUser;

public class SysUserDto extends SysUser {

    private String captcha;

    private boolean rememberMe;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
