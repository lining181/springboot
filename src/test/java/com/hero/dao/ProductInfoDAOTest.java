package com.hero.dao;

import com.hero.dataobject.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author lining
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDAOTest {

    @Resource
    private ProductInfoDAO infoDAO;

    @Test
    public void test1(){
        List<ProductInfo> all = infoDAO.findAll();
        System.out.println(all.toString());
    }

    @Test
    public void test2(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("大哥吃饭");
        productInfo.setProductPrice(new BigDecimal(32));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("吃一口喷香");
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);

        ProductInfo info = infoDAO.save(productInfo);
        System.out.println(info.toString());
    }

    @Test
    public void test3(){
        List<ProductInfo> productInfoList = infoDAO.findByProductStatus(0);
        System.out.println(productInfoList.toString());
    }

    @Test
    public void test4(){
        ProductInfo productInfo = infoDAO.findByProductId("123456");

        productInfo.setProductName("撸串串");

        ProductInfo productInfo1 = infoDAO.save(productInfo);

        System.out.println(productInfo1);
    }

}