package com.hero.services.impl;

import com.hero.dao.SellerInfoDAO;
import com.hero.dataobject.SellerInfo;
import com.hero.services.SellerService;
import com.hero.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @program: springboot
 * @description:
 * @author: LiNing
 * @create: 2019-04-11 12:08
 **/
@Service
public class SellerServiceImpl implements SellerService{

    @Autowired
    private SellerInfoDAO sellerInfoDAO;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {

        SellerInfo sellerInfo = sellerInfoDAO.findByOpenid(openid);

        return sellerInfo;
    }

    @Override
    public void createSellerInfo() {

    }
}