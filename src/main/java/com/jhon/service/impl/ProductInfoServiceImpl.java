package com.jhon.service.impl;

import com.jhon.dao.ProductInfoDAO;
import com.jhon.dto.CartDTO;
import com.jhon.entity.ProductInfoDO;
import com.jhon.enums.ProductStatusEnum;
import com.jhon.enums.ResultEnum;
import com.jhon.exception.SellException;
import com.jhon.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>功能描述</br> 商品的业务逻辑实现接口 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ProductInfoServiceImpl
 * @date 2017/9/14 16:10
 */
@Service
@Slf4j
public class ProductInfoServiceImpl implements ProductInfoService {

	@Autowired
	private ProductInfoDAO productInfoDAO;

	@Override
	//@Cacheable(cacheNames = "product",key = "12345")
	public ProductInfoDO findOne(String productId) {
		return productInfoDAO.findOne(productId);
	}

	@Override
	public List<ProductInfoDO> findUpAll() {
		return productInfoDAO.findByProductStatus(ProductStatusEnum.UP.getCode());
	}

	@Override
	public Page<ProductInfoDO> findAll(Pageable pageable) {
		return productInfoDAO.findAll(pageable);
	}

	@Override
	@Transactional
	//@CachePut(cacheNames = "product",key = "12345")
	public ProductInfoDO save(ProductInfoDO productInfo) {
		return productInfoDAO.save(productInfo);
	}

	@Override
	@Transactional
	public void increaseStock(List<CartDTO> cartDTOList) {
		List<ProductInfoDO> productInfoList = new ArrayList<ProductInfoDO>();
		for (CartDTO cartDTO : cartDTOList) {
			ProductInfoDO productInfo = productInfoDAO.findOne(cartDTO.getProductId());
			if( productInfo == null){
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
			productInfo.setProductStock(result);
			productInfoList.add(productInfo);
		}

		/** 判断是否为空 **/
		if(!CollectionUtils.isEmpty(productInfoList)){
			productInfoDAO.save(productInfoList);
		}
	}

	@Override
	@Transactional
	public void decreaseStock(List<CartDTO> cartDTOList) {
		List<ProductInfoDO> productInfoList = new ArrayList<ProductInfoDO>();
		for (CartDTO cartDTO : cartDTOList) {
			ProductInfoDO productInfo = productInfoDAO.findOne(cartDTO.getProductId());
			if( productInfo == null){
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
			productInfo.setProductStock(result);
			productInfoList.add(productInfo);
		}

		/** 判断是否为空 **/
		if(!CollectionUtils.isEmpty(productInfoList)){
			productInfoDAO.save(productInfoList);
		}
	}

	@Override
	@Transactional
	public ProductInfoDO onSale(String productId) {
		ProductInfoDO productInfo = productInfoDAO.findOne(productId);
		if(productInfo == null){
			throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
		}

		if(productInfo.getProductStatusEnum() == ProductStatusEnum.UP){
			throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
		}

		/** 更新 **/
		productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
		return productInfoDAO.save(productInfo);
	}

	@Override
	@Transactional
	public ProductInfoDO offSale(String productId) {
		ProductInfoDO productInfo = productInfoDAO.findOne(productId);
		if(productInfo == null){
			throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
		}

		if(productInfo.getProductStatusEnum() == ProductStatusEnum.UP){
			throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
		}

		/** 更新 **/
		productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
		return productInfoDAO.save(productInfo);
	}
}
