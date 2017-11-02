package com.jhon.service;

import com.jhon.dto.OrderDTO;

/**
 * <p>功能描述</br> 消息推送逻辑接口 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName PushMessageService
 * @date 2017/9/17 14:28
 */
public interface PushMessageService {
	/**
	 * 订单状态变更消息
	 *
	 * @param orderDTO
	 */
	void orderStatus(OrderDTO orderDTO);
}
