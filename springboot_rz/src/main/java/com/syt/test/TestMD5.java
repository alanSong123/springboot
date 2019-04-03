package com.syt.test;

import org.apache.shiro.crypto.hash.Md5Hash;

public class TestMD5 {

    public static void main(String[]args){

        Md5Hash md5Hash = new Md5Hash("admin","admin",1024);
        System.out.println(md5Hash.toString());
        Md5Hash md5Hash2 = new Md5Hash("jerry","jerry",1024);
        System.out.println(md5Hash2.toString());
    }
}
