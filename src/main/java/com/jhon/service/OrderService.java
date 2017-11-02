package com.jhon.service;

import com.jhon.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * <p>功能描述</br> 订单业务逻辑接口 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName OrderService
 * @date 2017/9/14 14:56
 */
public interface OrderService {

	/** 创建订单 **/
	OrderDTO create(OrderDTO orderDTO);

	/** 查询单个订单 **/
	OrderDTO findOne(String orderId);

	/** 查询订单列表 (买家使用)**/
	Page<OrderDTO> findList(String buyerOpenId, Pageable pageable);

	/** 取消订单 **/
	OrderDTO cancle(OrderDTO orderDTO);

	/** 完结订单 **/
	OrderDTO finish(OrderDTO orderDTO);

	/** 支付订单 **/
	OrderDTO paid(OrderDTO orderDTO);

	/** 查询订单列表(卖家使用) **/
	Page<OrderDTO> findList(Pageable pageable);
}
