package com.hero.services.impl;

import com.hero.dataobject.ProductInfo;
import com.hero.enums.ProductStatusEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author lining
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Test
    public void findOne() {
        ProductInfo one = productInfoService.findOne("123456");
        System.out.println(one);
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        System.out.println(productInfoList);
    }

    @Test
    public void findAll() {
        PageRequest request = new PageRequest(0, 2);
        Page<ProductInfo> productInfos = productInfoService.findAll(request);
        System.out.println(productInfos);
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123457");
        productInfo.setProductName("大锅炖");
        productInfo.setProductPrice(new BigDecimal(56.30));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("炖一锅");
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategoryType(2);


        ProductInfo save = productInfoService.save(productInfo);
        System.out.println(save);

    }

    @Test
    public void online() {
        ProductInfo productInfo = productInfoService.online("123456");
        System.out.println(productInfo);
    }

    @Test
    public void offline() {
        ProductInfo productInfo = productInfoService.offline("123456");
        System.out.println(productInfo);
    }
}