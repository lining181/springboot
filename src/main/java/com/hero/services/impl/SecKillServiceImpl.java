package com.hero.services.impl;

import com.hero.annotation.AccessLimit;
import com.hero.dao.OrderMasterDAO;
import com.hero.exception.MyException;
import com.hero.services.SecKillService;
import com.hero.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @program: springboot
 * @description: 秒杀实现类
 * @author: LiNing
 * @create: 2019-04-11 14:09
 **/
@Service
@Slf4j
public class SecKillServiceImpl implements SecKillService{

    @Autowired
    private StringRedisTemplate redisTemplate;

    private ConcurrentLinkedDeque list = new ConcurrentLinkedDeque();

    @AccessLimit(seconds = 5, maxCount = 5)
    @Override
    public Integer secKill(String productid) {

        //预减库存 -1 和 -3个
        //redisTemplate.boundValueOps(productid).decrement(3);
        Long decrement = redisTemplate.boundValueOps(productid).decrement();

        if (decrement < 0) {
            throw new MyException(100,"已经抢完了");
        }
        //todo 发送mq去将秒杀消息存入消息表

        //下单
        list.add(KeyUtil.genUniqueKey());
        System.out.println(list);
        return decrement.intValue();
    }

}