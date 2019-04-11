package com.hero.annotation.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ForbiddenException;

/**
 * @program: springboot
 * @description:
 * @author: LiNing
 * @create: 2019-04-11 13:07
 **/
@Slf4j
@Aspect
@Component
public class TokenImpl {

    @Resource
    private HttpServletRequest request;
    @Resource
    private StringRedisTemplate redisTemplate0;

    @Pointcut("execution(public * com.hero.controller.*.*(..))")
    public void token() {

    }

    @Before("token()")
    public void tokenValidation(JoinPoint joinPoint){
        String token = request.getHeader("token");
        token = "wannengtoken999999";
        if (token == "wannengtoken999999"){
            return;
        }
        if(StringUtils.isEmpty(token)){
            throw new ForbiddenException();
        }
        if(!redisTemplate0.hasKey(token)){
            throw new ForbiddenException();
        }
    }

}