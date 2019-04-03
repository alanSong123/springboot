package com.syt.realm;

import com.syt.entity.SysUser;
import com.syt.service.SysMenuService;
import com.syt.service.SysRoleService;
import com.syt.service.SysUsersService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component(value = "userRealm") //衍生三个注解@Repository @Service @Controller
public class UserRealm extends AuthorizingRealm {

    @Resource
    private SysUsersService sysUsersService;

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysMenuService sysMenuService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("------->>>授权");
        SysUser user = (SysUser) principalCollection.getPrimaryPrincipal();

        List<String> roles = sysRoleService.findRolesByUserId(user.getUserId());
        List<String> perms = sysMenuService.findPermsByUserId(user.getUserId());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(perms);
        System.out.println("------->>>授权over");
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("------>>>认证");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String uname = token.getUsername();
        String pass = new String(token.getPassword());

        SysUser user = sysUsersService.findByUname(uname);
        if(user == null){
            throw new UnknownAccountException("账号未知");
        }
        if(!user.getPassword().equals(pass)){
            throw new IncorrectCredentialsException("密码错误");
        }
        if(user.getStatus() == 0){
            throw  new LockedAccountException("账号被冻结");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,pass,this.getName());

        return info;
    }
}
