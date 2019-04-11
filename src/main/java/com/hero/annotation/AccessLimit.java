package com.hero.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @program: springboot
 * @description:
 * @author: LiNing
 * @create: 2019-04-11 13:38
 **/
@Retention(RUNTIME)
@Target(METHOD)
public @interface AccessLimit {

    int seconds();
    int maxCount();
    //实际应该是true，需要登陆
    //boolean needLogin() default false;

}