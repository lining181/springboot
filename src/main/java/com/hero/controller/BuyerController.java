package com.hero.controller;

import com.hero.dataobject.ProductCategory;
import com.hero.dataobject.ProductInfo;
import com.hero.enums.ResultEnum;
import com.hero.services.CategoryService;
import com.hero.services.ProductInfoService;
import com.hero.utils.ResultVOUtil;
import com.hero.vo.ProductInfoVO;
import com.hero.vo.ProductVO;
import com.hero.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: springboot
 * @description:
 * @author: LiNing
 * @create: 2019-04-10 16:16
 **/
@Slf4j
@Controller
@RequestMapping(value = "/buyer/product")
public class BuyerController {

    @Resource
    private ProductInfoService productInfoService;
    @Resource
    private CategoryService categoryService;

    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResultVO list(){
        ResultVO result = new ResultVO();

        //查询所有上架的商品
        List<ProductInfo> productInfoS = productInfoService.findUpAll();
        //查询所有类别
        List<Integer> categoryTypeList = productInfoS.stream()
                .map(p -> p.getCategoryType()).collect(Collectors.toList());

        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        //组装数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : categoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOS = new ArrayList<>();
            for (ProductInfo productInfo : productInfoS) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOS.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOS);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }
}