package com.hero.dao;

import com.hero.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: springboot
 * @description: 卖家信息DAO
 * @author: LiNing
 * @create: 2019-04-11 11:47
 **/
public interface SellerInfoDAO extends JpaRepository<SellerInfo,String>{

    SellerInfo findByOpenid(String openid);

}
