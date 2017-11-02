package com.jhon.service;

import com.jhon.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

/**
 * <p>功能描述</br> 支付的业务逻辑接口的定义 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName PayService
 * @date 2017/9/14 20:13
 */
public interface PayService {

	/** 创建订单 **/
	PayResponse create(OrderDTO orderDTO);

	/** 通知 **/
	PayResponse notify(String notifyData);

	/** 退款的接口 **/
	RefundResponse refund(OrderDTO orderDTO);


}
