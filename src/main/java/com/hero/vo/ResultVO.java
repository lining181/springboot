package com.hero.vo;

import lombok.Data;

/**
 * @program: springboot
 * @description: 返回给前端的视图对象
 * @author: LiNing
 * @create: 2019-04-10 16:12
 **/
@Data
public class ResultVO {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 数据
     */
    private Object data;






}