package com.syt.anno;

public class MyAnno {
    private int age;
    @Deprecated
    public int getAge() {
        return age;
    }

    @Deprecated
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "MyAnno{" +
                "age=" + age +
                '}';
    }
}
