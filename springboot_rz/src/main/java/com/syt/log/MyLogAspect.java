package com.syt.log;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.druid.util.HttpClientUtils;
import com.alibaba.fastjson.JSON;
import com.syt.entity.SysLog;
import com.syt.service.SysLogService;
import com.syt.utils.HttpContextUtils;
import com.syt.utils.IPUtils;
import com.syt.utils.ShiroUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class MyLogAspect {
    
    @Resource
    private SysLogService sysLogService;

    @Pointcut(value = "@annotation(com.syt.log.Mylog)")
    public void myPointCut(){
        
    }
    
    @AfterReturning(pointcut = "myPointCut()")
    public void after(JoinPoint joinPoint){
//        System.out.println("后置增强"+joinPoint.getTarget()+joinPoint.getSignature());
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        Method method = signature.getMethod();
        Mylog mylog = method.getAnnotation(Mylog.class);
        String uname = ShiroUtils.getCurrentUser().getUsername();
        String opration = mylog.value();
        String methodName = joinPoint.getTarget().getClass()+"."+joinPoint.getSignature().getName();
        String params = JSON.toJSONString(joinPoint.getArgs());
        String ip = IPUtils.getIpAddr(HttpContextUtils.getHttpServletRequest());

        SysLog sysLog = new SysLog();
        sysLog.setCreateDate(new Date());
        sysLog.setIp(ip);
        sysLog.setMethod(methodName);
        sysLog.setParams(params);
        sysLog.setUsername(uname);
        sysLog.setOperation(opration);

        int i = sysLogService.saveLog(sysLog);
        
        System.out.println(i>0?"保存日志成功":"失败");
    }


}
