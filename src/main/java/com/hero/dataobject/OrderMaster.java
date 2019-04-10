package com.hero.dataobject;

import com.hero.enums.OrderStatusEnum;
import com.hero.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: springboot
 * @description: 订单主体
 * @author: LiNing
 * @create: 2019-04-10 17:30
 **/
@Entity
@Data
@DynamicUpdate
public class OrderMaster implements Serializable{

    private static final long serialVersionUID = 3513245906771131791L;
    /**
     * 订单id
     */
    @Id
    private String orderId;

    /**
     * 买家名字
     */
    private String buyerName;

    /**
     * 买家手机号
     */
    private String buyerPhone;

    /**
     * 买家地址
     */
    private String buyerAddress;

    /**
     * 买家微信Openid.
     */
    private String buyerOpenid;

    /**
     * 订单总金额.
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态, 默认为0新下单.
     */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /**
     *支付状态, 默认为0未支付
     */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间.
     */
    private Date updateTime;

}