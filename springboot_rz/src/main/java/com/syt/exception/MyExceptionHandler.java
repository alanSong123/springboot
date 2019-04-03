package com.syt.exception;


import com.syt.utils.R;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//跳页面
//@ControllerAdvice
//相应json
@RestControllerAdvice
public class MyExceptionHandler {



    @ExceptionHandler(AuthorizationException.class)
    public R handlerException(AuthorizationException e){
        return R.error("你没有权限");
    }

    @ExceptionHandler(RZEexception.class)
    public R handlerException(RZEexception e){
        return R.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public R handlerException(Exception e){
        return R.error(e.getMessage());
    }

}
