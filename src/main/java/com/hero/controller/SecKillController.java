package com.hero.controller;

import com.hero.dataobject.OrderMaster;
import com.hero.services.SecKillService;
import com.hero.utils.ResultVOUtil;
import com.hero.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: springboot
 * @description: 秒杀
 * @author: LiNing
 * @create: 2019-04-11 13:36
 **/
@Controller
@RequestMapping(value = "/buy")
@Slf4j
public class SecKillController {

    @Autowired
    private SecKillService secKillService;

    @ResponseBody
    @RequestMapping(value = "/kill" ,method = RequestMethod.POST)
    public ResultVO secKill(){
        Integer stock = secKillService.secKill("key_1");
        return  ResultVOUtil.success(stock);
    }
}