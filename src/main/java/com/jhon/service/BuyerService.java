package com.jhon.service;

import com.jhon.dto.OrderDTO;

/**
 * <p>功能描述</br> 买家业务逻辑接口定义 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName BuyerService
 * @date 2017/9/14 19:30
 */
public interface BuyerService {

	/**
	 * 查询唯一的订单
	 * @param openid 买家的微信openId
	 * @param orderId 订单Id
	 * @return
	 */
	OrderDTO findOrderOne(String openid, String orderId);

	/**
	 * 取消订单操作
	 * @param openid
	 * @param orderId
	 * @return
	 */
	OrderDTO cancleOrder(String openid, String orderId);
}
