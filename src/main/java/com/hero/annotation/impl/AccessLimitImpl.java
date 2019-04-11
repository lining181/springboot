package com.hero.annotation.impl;

import com.hero.annotation.AccessLimit;
import com.hero.exception.AccessLimitException;
import com.hero.utils.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @program: springboot
 * @description:
 * @author: LiNing
 * @create: 2019-04-11 13:45
 **/
@Slf4j
@Aspect
@Component
public class AccessLimitImpl {

    @Resource
    private HttpServletRequest request;
    @Resource
    private StringRedisTemplate redisTemplate0;


    @Pointcut("execution(public * com.hero.controller.JJJ*.*(..))")
    public void accessLimiter() {

    }

    @Before("accessLimiter()")
    public void redisLockRequest(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        AccessLimit accessLimit = method.getAnnotation(AccessLimit.class);
        if (null == accessLimit) {
            throw new AccessLimitException(AccessLimitException.CODE_ANNOTATION, "限流注解异常");
        }
        //redis value 限流的具体参数
        int limitCount = accessLimit.maxCount();
        long limitTimeOut = accessLimit.seconds();
        log.info("[RedisLimitImpl] [redisLockRequest] [limitCount=] [{}] [limitTimeOut=] [{}]", limitCount, limitTimeOut);
        //redis key 组成 这里用ip加接口地址组成
        String redis_key = IpUtil.getIpAddr(request) + request.getRequestURI();
        if (!redisTemplate0.hasKey(redis_key)) {
            redisTemplate0.opsForValue().increment(redis_key, 1);
            //设置有效时间
            redisTemplate0.expire(redis_key, limitTimeOut, TimeUnit.MILLISECONDS);
        } else {
            Object str = redisTemplate0.opsForValue().get(redis_key);
            if (null == str) {
                //如果等于空的话放行，不影响其他用户体验，刚好压住时间线
            } else {
                log.info("[RedisLimitImpl] [redisLockRequest] [str=] [{}]", str);
                int count = Integer.valueOf(str.toString()).intValue();
                if (count >= limitCount) {
                    throw new AccessLimitException(AccessLimitException.CODE_LIMIT, "调用频繁");
                } else {
                    redisTemplate0.opsForValue().increment(redis_key, 1);
                    //设置有效时间
                    redisTemplate0.expire(redis_key, limitTimeOut, TimeUnit.MILLISECONDS);
                }
            }
        }
    }
}