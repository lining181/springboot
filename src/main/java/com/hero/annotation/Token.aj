package com.hero.annotation;

import java.lang.annotation.*;

/**
 * @Author lining
 *  @Target({ElementType.METHOD})   作用在方法上
    @Retention(RetentionPolicy.RUNTIME)  什么时候起作用
    @Documented
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Token {

}
