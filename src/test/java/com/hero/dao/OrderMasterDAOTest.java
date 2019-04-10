package com.hero.dao;

import com.hero.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @Author lining
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDAOTest {

    @Resource
    private OrderMasterDAO orderMasterDAO;

    private static String OPENID = "111111";

    @Test
    public void test1() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("0000001");
        orderMaster.setBuyerName("宁哥");
        orderMaster.setBuyerPhone("18888888888");
        orderMaster.setBuyerAddress("浙江");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(2.5));

        OrderMaster result = orderMasterDAO.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void test2() {
        Optional<OrderMaster> orderMaster = orderMasterDAO.findById("0000001");
        System.out.println(orderMaster);
    }

    @Test
    public void test3() {

    }
    @Test
    public void test4() {

    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest request = new PageRequest(1, 3);

        Page<OrderMaster> result = orderMasterDAO.findByBuyerOpenid(OPENID, request);

        Assert.assertNotEquals(0, result.getTotalElements());
    }
}