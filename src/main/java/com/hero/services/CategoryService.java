package com.hero.services;

import com.hero.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.CodePointLength;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: springboot
 * @description:
 * @author: LiNing
 * @create: 2019-04-10 14:30
 **/
@Component
public interface CategoryService {
    /**
     * 根据主键ID查询商品类目
     * @return
     */
    ProductCategory findById(String categoryId);

    /**
     * 查询所有
     * @return
     */
    List<ProductCategory> findAll();

    /**
     * 根据类别查询商品类目集合
     * @param categoryTypeS
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeS);

    /**
     * 增加或修改一个
     * @param category
     * @return
     */
    ProductCategory save(ProductCategory category);

}
