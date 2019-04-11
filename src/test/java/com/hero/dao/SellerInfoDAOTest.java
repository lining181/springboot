package com.hero.dao;

import com.hero.dataobject.SellerInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author lining
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoDAOTest {

    @Autowired
    private SellerInfoDAO sellerInfoDAO;


    @Test
    public void findByOpenid() {
        SellerInfo sellerInfo = sellerInfoDAO.findByOpenid("222222");
        System.out.println(sellerInfo.toString());
    }

    @Test
    public void testSave(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setId("222222");
        sellerInfo.setOpenid("222222");
        sellerInfo.setPassword("222222");
        sellerInfo.setUsername("小小宁");

        sellerInfoDAO.save(sellerInfo);
    }
}