package com.hero.exception;

import lombok.Data;

/**
 * @author zhangdi
 * @description
 * @date 2018-08-29
 */

@Data
public class AccessLimitException extends RuntimeException{

    /**
     * 超出了限制次数
     */
    public static final byte CODE_LIMIT = (byte)101;

    /**
     * 限流注解异常
     */
    public static final byte CODE_ANNOTATION = (byte)102;

    private byte code;

    private String msg;

    public AccessLimitException(byte code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public AccessLimitException(String msg) {
        this.msg = msg;
    }

    public AccessLimitException() {

    }
}
