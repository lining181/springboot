package com.hero.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: springboot
 * @description: 商品类目
 * @author: LiNing
 * @create: 2019-04-10 13:24
 *
 * "@DynamicUpdate" 动态更新（不加的话时间会不更新）
 **/
@Entity
@DynamicUpdate
@Data
public class ProductCategory implements Serializable{

    private static final long serialVersionUID = -8833275936462488423L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * 商品类目id
     * "@Id" 主键
     * "@GeneratedValue" 自增
     */
    private Integer categoryId;

    /**
     * 类目名字
     */
    private String categoryName;

    /**
     * 类目编号 0代表通用 1代表男分组 2代表女分组
     */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}