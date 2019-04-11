package com.hero.services;

import com.hero.dataobject.SellerInfo;

/**
 * @program: springboot
 * @description:
 * @author: LiNing
 * @create: 2019-04-11 12:07
 **/
public interface SellerService {
    /**
     * 通过openid查询卖家信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);

    /**
     * 创建用户
     */
    void createSellerInfo();

}
