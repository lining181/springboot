package com.hero.services.impl;

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
public class SecKillServiceImplTest {

    @Autowired
    private SecKillServiceImpl secKillService;

    @Test
    public void secKill() {

        Integer stock = secKillService.secKill("key_1");

        System.out.println(stock);

    }
}