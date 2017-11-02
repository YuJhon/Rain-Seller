package com.jhon.service;

import com.jhon.dto.CartDTO;
import com.jhon.entity.ProductInfoDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * <p>功能描述</br> 商品业务逻辑接口定义 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ProductInfoService
 * @date 2017/9/14 16:10
 */
public interface ProductInfoService {

	/**
	 * 根据Id查询记录
	 * @param productId
	 * @return
	 */
	ProductInfoDO findOne(String productId);

	/**
	 * 查询所有记录
	 * @return
	 */
	List<ProductInfoDO> findUpAll();

	/**
	 * 分页查询所有记录
	 * @param pageable
	 * @return
	 */
	Page<ProductInfoDO> findAll(Pageable pageable);

	/**
	 * 新增商品信息
	 * @param productInfo
	 * @return
	 */
	ProductInfoDO save(ProductInfoDO productInfo);

	/**
	 * 添加库存
	 * @param cartDTOList
	 */
	void increaseStock(List<CartDTO> cartDTOList);

	/**
	 * 减少库存
	 * @param cartDTOList
	 */
	void decreaseStock(List<CartDTO> cartDTOList);

	/**
	 * 上架
	 * @param productId
	 * @return
	 */
	ProductInfoDO onSale(String productId);

	/**
	 * 下架
	 * @param productId
	 * @return
	 */
	ProductInfoDO offSale(String productId);

}
