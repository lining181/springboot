package com.hero.controller;

import com.hero.converter.OrderForm2OrderDTOConverter;
import com.hero.dto.OrderDTO;
import com.hero.enums.ResultEnum;
import com.hero.exception.MyException;
import com.hero.form.OrderForm;
import com.hero.services.OrderService;
import com.hero.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.hero.vo.ResultVO;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: springboot
 * @description: 买家订单Controller
 * @author: LiNing
 * @create: 2019-04-11 10:48
 **/
@Controller
@RequestMapping(value = "/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     * @param orderForm
     * @param bindingResult
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResultVO create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("[BuyerOrderController][create][参数不正确], orderForm={}", orderForm);
            throw new MyException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("[BuyerOrderController][create][购物车不能为空]");
            throw new MyException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultVOUtil.success(map);
    }



}