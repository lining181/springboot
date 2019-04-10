package com.hero.enums;

import lombok.Getter;

/**
 * @program: springboot
 * @description: 支付状态
 * @author: LiNing
 * @create: 2019-04-10 14:26
 **/
@Getter
public enum PayStatusEnum implements CodeEnum {

    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功");

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
