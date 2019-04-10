package com.hero.services.impl;

import com.hero.dao.ProductInfoDAO;
import com.hero.dataobject.ProductInfo;
import com.hero.dto.CartDTO;
import com.hero.enums.ProductStatusEnum;
import com.hero.enums.ResultEnum;
import com.hero.exception.MyException;
import com.hero.services.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: springboot
 * @description:商品详情
 * @author: LiNing
 * @create: 2019-04-10 15:37
 **/
@Service
@Slf4j
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoDAO infoDAO;

    @Override
    public ProductInfo findOne(String productId) {
        return infoDAO.findByProductId(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return infoDAO.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return infoDAO.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return infoDAO.save(productInfo);
    }

    @Override
    public ProductInfo online(String productId) {
        ProductInfo productInfo = infoDAO.findByProductId(productId);
        if (productInfo == null) {
            log.info("[ProductInfoServiceImpl][online]商品不存在，productId为{}",productId);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP) {
            log.info("[ProductInfoServiceImpl][online]商品状态不对，productId为{}",productId);
        }
        //更新
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return infoDAO.save(productInfo);
    }

    @Override
    public ProductInfo offline(String productId) {
        ProductInfo productInfo = infoDAO.findByProductId(productId);
        if (productInfo == null) {
            log.info("[ProductInfoServiceImpl][online]商品不存在，productId为{}",productId);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN) {
            log.info("[ProductInfoServiceImpl][online]商品状态不对，productId为{}",productId);
        }

        //更新
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return infoDAO.save(productInfo);
    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList) {
            ProductInfo productInfo = infoDAO.findByProductId(cartDTO.getProductId());
            if (productInfo == null) {
                throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new MyException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);

            infoDAO.save(productInfo);
        }
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList) {
            ProductInfo productInfo = infoDAO.findByProductId(cartDTO.getProductId());
            if (productInfo == null) {
                throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);

            infoDAO.save(productInfo);
        }
    }

}