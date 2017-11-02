package com.jhon.service.impl;

import com.jhon.dto.OrderDTO;
import com.jhon.enums.ResultEnum;
import com.jhon.exception.SellException;
import com.jhon.service.BuyerService;
import com.jhon.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>功能描述</br> 买家业务逻辑接口的实现类 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName BuyerServiceImpl
 * @date 2017/9/14 19:30
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

	@Autowired
	private OrderService orderService;

	@Override
	public OrderDTO findOrderOne(String openid, String orderId) {
		return checkOrderOwner(openid, orderId);
	}

	@Override
	public OrderDTO cancleOrder(String openid, String orderId) {
		OrderDTO orderDTO = checkOrderOwner(openid, orderId);
		if (orderDTO == null) {
			log.error("【取消订单】查询不到该订单，OrderId={}", orderId);
			throw new SellException(ResultEnum.ORDER_NOT_EXIST);
		}
		return orderService.cancle(orderDTO);
	}

	/**
	 * 校验当前订单是否属于当前操作用户
	 *
	 * @param openid  买家openid
	 * @param orderId 订单Id
	 * @return
	 */
	private OrderDTO checkOrderOwner(String openid, String orderId) {
		OrderDTO orderDTO = orderService.findOne(orderId);
		if (orderDTO == null) {
			return null;
		}
		/** 判断是否是自己的订单 **/
		if (!orderDTO.getBuyerOpenId().equalsIgnoreCase(openid)) {
			log.error("【查询订单】订单的openid不一致，openid = {}, orderDTO = {}", openid, orderDTO);
			throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
		}
		return orderDTO;
	}
}
