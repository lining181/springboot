package com.hero.utils;

import java.util.Random;

/**
 * @program: springboot
 * @description:
 * @author: LiNing
 * @create: 2019-04-10 19:21
 **/
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式: 时间+随机数
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}