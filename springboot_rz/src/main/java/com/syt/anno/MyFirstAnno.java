package com.syt.anno;


import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MyFirstAnno {

    String name() default "anno";

    String value() default "我的第一个自定义注解";


}
