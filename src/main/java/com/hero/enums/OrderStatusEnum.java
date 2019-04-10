package com.hero.enums;

import lombok.Getter;

/**
 * @program: springboot
 * @description: 订单状态
 * @author: LiNing
 * @create: 2019-04-10 14:26
 **/
@Getter
public enum OrderStatusEnum implements CodeEnum {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消");

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
