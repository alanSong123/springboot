package com.syt.quartz.task;

import org.springframework.stereotype.Component;

@Component(value = "unLockAccount")
public class UnLockAccount {
    
    public void unLock(){
        System.out.println("解封账户");
    }
    
}
