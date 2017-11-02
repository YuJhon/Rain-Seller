package com.jhon.service;

import com.jhon.entity.ProductCategoryDO;

import java.util.List;

/**
 * <p>功能描述</br> 商品类目业务逻辑接口 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ProductCategoryService
 * @date 2017/9/14 21:11
 */
public interface ProductCategoryService {

	/**
	 * 查询单一的类目详情
	 * @param categoryId
	 * @return
	 */
	ProductCategoryDO findOne(Integer categoryId);

	/**
	 * 查询所有的类目
	 * @return
	 */
	List<ProductCategoryDO> findAll();

	/**
	 * 通过类型批量查询商品类目
	 * @param catetoryTypeList
	 * @return
	 */
	List<ProductCategoryDO> findByCategoryTypeIn(List<Integer> catetoryTypeList);

	/**
	 * 保存对象
	 * @param productCategoryDO
	 * @return
	 */
	ProductCategoryDO save(ProductCategoryDO productCategoryDO);
}
