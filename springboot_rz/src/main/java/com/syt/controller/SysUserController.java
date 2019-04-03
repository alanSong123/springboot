package com.syt.controller;


import com.google.code.kaptcha.Producer;
import com.syt.dto.SysUserDto;
import com.syt.entity.SysUser;
import com.syt.log.Mylog;
import com.syt.service.SysUsersService;
import com.syt.utils.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.List;

@RestController
public class SysUserController {

    @Resource
    private Producer producer;

    @Resource
    private SysUsersService sysUsersService;

    @RequestMapping("/findAll")
    public List<SysUser> findAll(){
        return sysUsersService.findAll();
    }

    @RequestMapping("/sys/login")
    public R login(@RequestBody SysUserDto sysUser){
        String s = null;
        String code  = ShiroUtils.getCaptcha();
        String c = sysUser.getCaptcha();
        if(code != null && !code.equalsIgnoreCase(c)){
            return R.error("验证码错误");
        }
        //得到subject
        try{
            Subject subject = SecurityUtils.getSubject();
            String pwd = sysUser.getPassword();
            Md5Hash md5Hash = new Md5Hash(pwd,sysUser.getUsername(),1024);
            pwd = md5Hash.toString();
            //把用户名和密码封装成UsernamePasswordToken对象
            UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUsername(),pwd);

            token.setRememberMe(sysUser.isRememberMe());

            subject.login(token);
            System.out.println(subject.hasRole("管理员"));
            return R.ok();
        }catch(Exception e){
            e.printStackTrace();
            s = e.getMessage();
        }
        return R.error(s);
       // return sysUsersService.login(sysUser);
    }
    @Mylog(value = "用户列表",description = "分页显示用户")
    @RequiresPermissions("sys:user:list")
    @RequestMapping("/sys/user/list")
    public ResultData findUserByPage(AboutPage aboutPage,
                                     String search,
                                     AboutSort aboutSort){
        return sysUsersService.findByPage(aboutPage,search,aboutSort);
    }

    @RequestMapping("/captcha.jpg")
    public void captche(HttpServletResponse response){
        try{
            String text = producer.createText();
            System.out.println("验证码---->>>"+text);
            ShiroUtils.setAttribute("code",text);
            BufferedImage image = producer.createImage(text);

            OutputStream os = response.getOutputStream();
            ImageIO.write(image,"jpg",os);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    @Mylog(value = "查询用户信息",description = "显示用户信息")
    //@RequiresPermissions("sys:user:select")
    @RequestMapping("/sys/user/info")
    public R userInfo(){
        System.out.println("===============================");
        SysUser user = ShiroUtils.getCurrentUser();
        System.out.println("-----------"+user.getUsername());
        return R.ok().put("user",user);
    }




}
