package com.hero.exception;

import com.hero.enums.ResultEnum;

/**
 * @program: springboot
 * @description:
 * @author: LiNing
 * @create: 2019-04-10 19:30
 **/
public class MyException extends RuntimeException {

    private Integer code;

    public MyException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public MyException(Integer code, String message) {
        super(message);
        this.code = code;
    }

}