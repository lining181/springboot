package com.hero.dao;

import com.hero.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: springboot
 * @description: 商品详情
 * @author: LiNing
 * @create: 2019-04-10 15:13
 **/
public interface ProductInfoDAO extends JpaRepository<ProductInfo, String>{

    List<ProductInfo> findByProductStatus(Integer productStatus);

    ProductInfo findByProductId(String productId);


}
