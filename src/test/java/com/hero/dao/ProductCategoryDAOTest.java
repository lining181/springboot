package com.hero.dao;

import com.hero.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.io.DataOutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author lining
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryDAOTest {

    @Resource
    private ProductCategoryDAO categoryDAO;

    @Test
    public void test1(){
        ProductCategory category = categoryDAO.findById("1").get();
        Assert.assertNotNull(category);
    }

    @Test
    public void test2(){
        ProductCategory category = new ProductCategory();
        category.setCategoryName("你宁哥的");
        category.setCategoryType(1);

        ProductCategory save = categoryDAO.save(category);
        log.error(save.toString());
    }

    @Test
    public void test3(){
        ProductCategory category = categoryDAO.findById("1").get();
        category.setCategoryName("宁哥");
        ProductCategory save = categoryDAO.save(category);
        log.error(save.toString());
    }

    @Test
    public void test4(){
        List<Integer> list = Arrays.asList(1, 2);
        List<ProductCategory> categoryS = categoryDAO.findByCategoryTypeIn(list);
        for (ProductCategory category : categoryS) {
            log.error(category.toString());
        }

    }
}