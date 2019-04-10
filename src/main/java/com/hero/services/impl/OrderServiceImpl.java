package com.hero.services.impl;

import com.hero.dao.OrderDetailDAO;
import com.hero.dao.OrderMasterDAO;
import com.hero.dataobject.OrderDetail;
import com.hero.dataobject.OrderMaster;
import com.hero.dataobject.ProductInfo;
import com.hero.dto.CartDTO;
import com.hero.dto.OrderDTO;
import com.hero.enums.OrderStatusEnum;
import com.hero.enums.PayStatusEnum;
import com.hero.enums.ResultEnum;
import com.hero.exception.MyException;
import com.hero.services.OrderService;
import com.hero.services.ProductInfoService;
import com.hero.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: springboot
 * @description:
 * @author: LiNing
 * @create: 2019-04-10 19:15
 **/
@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailDAO orderDetailDAO;

    @Autowired
    private OrderMasterDAO orderMasterDAO;

    @Transactional
    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        //1. 查询商品（数量, 价格）
        for (OrderDetail orderDetail: orderDTO.getOrderDetailList()) {
            ProductInfo productInfo =  productInfoService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new  MyException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //2. 计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);

            //订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailDAO.save(orderDetail);
        }


        //3. 写入订单数据库（orderMaster和orderDetail）
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterDAO.save(orderMaster);

        //4.todo  扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOList);


        //5.todo 发送websocket消息

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        return null;
    }
}