package com.hero.dao;

import com.hero.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * @program: springboot
 * @description:
 * @author: LiNing
 * @create: 2019-04-10 13:26
 **/
public interface ProductCategoryDAO extends JpaRepository<ProductCategory,String> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> typeList );

}
