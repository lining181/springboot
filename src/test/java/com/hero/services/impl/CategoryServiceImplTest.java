package com.hero.services.impl;

import com.hero.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.persistence.Id;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author lining
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Resource
    private CategoryServiceImpl categoryService;

    @Test
    public void findById() throws Exception{

        ProductCategory id = categoryService.findById("1");
        System.out.println(id);
    }

    @Test
    public void findAll() {
        List<ProductCategory> all = categoryService.findAll();
        System.out.println(all);
    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> list = Arrays.asList(1, 2);
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(list);
        System.out.println(categoryList);
    }

    @Test
    public void save() {
        ProductCategory category = new ProductCategory();
        ProductCategory category1 = categoryService.save(category);
        System.out.println(category1);

    }
}