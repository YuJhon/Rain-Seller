package com.jhon.dao;

import com.jhon.entity.OrderDetailDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <p>功能描述</br> 订单详情数据访问层 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName OrderDetailDAO
 * @date 2017/9/14 12:08
 */
public interface OrderDetailDAO extends JpaRepository<OrderDetailDO,String>{
	/**
	 * 根据订单号查询订单详情
	 * @param orderId
	 * @return
	 */
	List<OrderDetailDO> findByOrderId(String orderId);
}
