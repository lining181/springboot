package com.hero.dto;

import lombok.Data;

/**
 * @program: springboot
 * @description: 购物车
 * @author: LiNing
 * @create: 2019-04-10 19:38
 **/
@Data
public class CartDTO {
    /**
     * 商品Id
     */
    private String productId;

    /**
     * 数量
     */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}