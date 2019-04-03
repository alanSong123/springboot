package com.syt.exception;

public class RZEexception extends  RuntimeException{

    public RZEexception(){}


    public RZEexception(String msg){
        super(msg);
    }

    public RZEexception(String msg,Throwable throwable){
        super(msg,throwable);
    }





}
