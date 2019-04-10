package com.hero.dao;

import com.hero.dataobject.OrderDetail;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: springboot
 * @description:
 * @author: LiNing
 * @create: 2019-04-10 19:03
 **/
public interface OrderDetailDAO extends JpaRepository<OrderDetail,String>{

    List<OrderDetail> findByOrderId(String orderId);

}