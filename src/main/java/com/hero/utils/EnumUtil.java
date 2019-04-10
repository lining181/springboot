package com.hero.utils;

import com.hero.enums.CodeEnum;

/**
 * @program: springboot
 * @description:
 * @author: LiNing
 * @create: 2019-04-10 15:10
 **/
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }

}