package com.hero.services.impl;

import com.hero.dao.ProductCategoryDAO;
import com.hero.dataobject.ProductCategory;
import com.hero.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: springboot
 * @description:
 * @author: LiNing
 * @create: 2019-04-10 14:36
 **/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private ProductCategoryDAO categoryDAO;

    @Override
    public ProductCategory findById(String categoryId) {
        ProductCategory productCategory = categoryDAO.findById(categoryId).get();

        return productCategory;
    }

    @Override
    public List<ProductCategory> findAll() {
        List<ProductCategory> categoryList = categoryDAO.findAll();
        return categoryList;
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeS) {
        List<ProductCategory> categoryList = categoryDAO.findByCategoryTypeIn(categoryTypeS);
        return categoryList;
    }

    @Override
    public ProductCategory save(ProductCategory category) {
        ProductCategory productCategory = categoryDAO.save(category);
        return productCategory;
    }
}