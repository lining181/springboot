package com.hero.enums;

import lombok.Getter;

/**
 * @program: springboot
 * @description: 商品状态
 * @author: LiNing
 * @create: 2019-04-10 14:26
 **/
@Getter
public enum ProductStatusEnum implements CodeEnum {
    UP(0, "在架"),
    DOWN(1, "下架");

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
