package com.jhon.dao;

import com.jhon.entity.OrderDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * <p>功能描述</br> 订单数据访问层</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName OrderDAO
 * @date 2017/9/14 12:09
 */
public interface OrderDAO extends JpaRepository<OrderDO,String> {
	/**
	 * 根据OpenId查询分页订单
	 * @param buyerOpenId
	 * @param pageable
	 * @return
	 */
	Page<OrderDO> findByBuyerOpenId(String buyerOpenId, Pageable pageable);
}
