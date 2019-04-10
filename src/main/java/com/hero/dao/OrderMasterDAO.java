package com.hero.dao;

import com.hero.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: springboot
 * @description: 订单主体DAO
 * @author: LiNing
 * @create: 2019-04-10 18:43
 **/
public interface OrderMasterDAO extends JpaRepository<OrderMaster,String>{

    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

}
