package com.hero.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @program: springboot
 * @description: 买家信息
 * @author: LiNing
 * @create: 2019-04-11 11:46
 **/
@Data
@Entity
public class SellerInfo {

    @Id
    private String id;

    private String username;

    private String password;

    private String openid;
}