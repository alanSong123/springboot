package com.syt.utils;

import java.util.HashMap;

public class R extends HashMap {

    private int code;
    private String msg;

    public R(){}

    public R(int code){
        this.put("code",code);
    }

    public R(int code,String msg){
        super.put("code",code);
        super.put("msg",msg);
    }

    public static R ok(){
        return new R(0);
    }

    public static R ok(String msg){
        return new R(0,msg);
    }

    public static R error(){
        return new R(1);
    }

    public static R error(String msg){
        return new R(1,msg);
    }

    public R put(String s,Object o){
        super.put(s,o);
        return this;
    }



}
