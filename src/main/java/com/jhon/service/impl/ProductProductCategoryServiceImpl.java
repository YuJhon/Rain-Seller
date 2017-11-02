package com.jhon.service.impl;

import com.jhon.dao.ProductCategoryDAO;
import com.jhon.entity.ProductCategoryDO;
import com.jhon.service.ProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>功能描述</br> 商品类目的业务逻辑实现</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName CategoryServiceImpl
 * @date 2017/9/14 21:12
 */
@Service
@Slf4j
public class ProductProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryDAO productCategoryDAO;

	@Override
	public ProductCategoryDO findOne(Integer categoryId) {
		return productCategoryDAO.findOne(categoryId);
	}

	@Override
	public List<ProductCategoryDO> findAll() {
		return productCategoryDAO.findAll();
	}

	@Override
	public List<ProductCategoryDO> findByCategoryTypeIn(List<Integer> catetoryTypeList) {
		return productCategoryDAO.findByCategoryTypeIn(catetoryTypeList);
	}

	@Override
	public ProductCategoryDO save(ProductCategoryDO productCategoryDO) {
		return productCategoryDAO.save(productCategoryDO);
	}
}
