package com.syt.utils;

import org.omg.CORBA.StringHolder;

public class Lg {

    public  static void log(String msg){
        System.err.println("------->>>"+msg);
    }

    public static  void log(String msg,String v){
        System.err.println("-------->>>"+msg+"--:"+v);
    }

}
