package com.jhon.dao;

import com.jhon.entity.ProductCategoryDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <p>功能描述</br> 产品类项目</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ProductCategoryDAO
 * @date 2017/9/14 12:09
 */
public interface ProductCategoryDAO extends JpaRepository<ProductCategoryDO,Integer> {
	/**
	 * 根据类型批量查询产品类目
	 * @param typeList
	 * @return
	 */
	List<ProductCategoryDO> findByCategoryTypeIn(List<Integer> typeList);
}
