package com.jhon.controller;

import com.jhon.dto.OrderDTO;
import com.jhon.enums.ResultEnum;
import com.jhon.exception.SellException;
import com.jhon.service.OrderService;
import com.jhon.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import jdk.nashorn.internal.ir.ReturnNode;
import org.mockito.internal.stubbing.answers.ReturnsElementsOf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * <p>功能描述</br> 支付的控制器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName PayController
 * @date 2017/9/16 16:18
 */
@Controller
@RequestMapping("/pay")
public class PayController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private PayService payService;

	/**
	 * 创建订单
	 * @param orderId 订单号
	 * @param returnUrl 回调地址
	 * @param map 返回的参数
	 * @return
	 */
	@GetMapping("/create")
	public ModelAndView create(@RequestParam("orderId") String orderId,
	                           @RequestParam("returnUrl") String returnUrl,
	                           Map<String,Object> map){
		/** 1.查询订单 **/
		OrderDTO orderDTO = orderService.findOne(orderId);
		if( orderDTO == null){
			throw new SellException(ResultEnum.ORDER_NOT_EXIST);
		}

		/** 2.发起支付 **/
		PayResponse payResponse = payService.create(orderDTO);
		map.put("payResponse",payResponse);
		map.put("returnUrl",returnUrl);
		return new ModelAndView("pay/create",map);
	}

	/**
	 * 订单消息通知
	 * @param notifyData
	 * @return
	 */
	@PostMapping("/notify")
	public ModelAndView notify(@RequestBody String notifyData){
		payService.notify(notifyData);
		return new ModelAndView("pay/success");
	}
}
