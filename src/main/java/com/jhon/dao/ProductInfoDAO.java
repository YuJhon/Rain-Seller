package com.jhon.dao;

import com.jhon.entity.ProductInfoDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <p>功能描述</br> 产品详情数据访问层 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ProductInfoDAO
 * @date 2017/9/14 12:09
 */
public interface ProductInfoDAO extends JpaRepository<ProductInfoDO,String> {

	/**
	 * 通过商品的状态查询商品列表
	 * @param productStatus
	 * @return
	 */
	List<ProductInfoDO> findByProductStatus(Integer productStatus);
}
