package com.syt.utils;

public class StringUtils {

    public static  boolean isEmpty(String str){
        if(str == null){
            return true;
        }
        if(str.trim().length() == 0){
            return true;
        }
        return false;
    }

    public static  boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

}
