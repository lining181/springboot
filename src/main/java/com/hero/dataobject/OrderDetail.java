package com.hero.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: springboot
 * @description: 订单详情
 * @author: LiNing
 * @create: 2019-04-10 18:02
 **/
@Entity
@Data
public class OrderDetail implements Serializable{

    private static final long serialVersionUID = 6854864401068712247L;

    @Id
    private String detailId;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 商品id
     */
    private String productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品单价
     */
    private BigDecimal productPrice;

    /**
     * 商品数量
     */
    private Integer productQuantity;

    /**
     *  商品小图
     */
    private String productIcon;
}