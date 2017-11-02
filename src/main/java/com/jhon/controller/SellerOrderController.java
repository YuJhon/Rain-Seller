package com.jhon.controller;

import com.jhon.dto.OrderDTO;
import com.jhon.enums.ResultEnum;
import com.jhon.exception.SellException;
import com.jhon.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * <p>功能描述</br> 买家端订单控制器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName SellerOrderController
 * @date 2017/9/15 11:31
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

	@Autowired
	private OrderService orderService;

	/**
	 * 查询订单列表
	 * @param page 当前页面
	 * @param size 每页的大小
	 * @param map 返回参数的载体
	 * @return
	 */
	@GetMapping("/list")
	public ModelAndView list(@RequestParam(value="page",defaultValue = "1") Integer page,
	                         @RequestParam(value="size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
		PageRequest pageRequest = new PageRequest(page -1 ,size);
		Page<OrderDTO> pageOrders = orderService.findList(pageRequest);
		map.put("orderDTOPage",pageOrders);
		map.put("currentPage",page);
		map.put("size",size);
		return new ModelAndView("/order/list",map);
	}

	/**
	 * 取消订单
	 * @param orderId 订单Id
	 * @param map 返回的参数
	 * @return
	 */
	@GetMapping("/cancle")
	public ModelAndView cancle(@RequestParam("orderId") String orderId,
	                           Map<String,Object> map){
		try{
			OrderDTO orderDTO = orderService.findOne(orderId);
			orderService.cancle(orderDTO);
		}catch(Exception e){
			log.error("【卖家端取消订单】发生异常{}",e);
			map.put("msg",e.getMessage());
			map.put("url","/seller/order/list");
			return new ModelAndView("common/error",map);
		}

		map.put("msg", ResultEnum.ORDER_CANCLE_SUCCESS.getMessage());
		map.put("url","/seller/order/list");
		return new ModelAndView("common/success",map);
	}

	/**
	 * 完成订单
	 * @param orderId 订单Id
	 * @param map 返回的参数
	 * @return
	 */
	@GetMapping("/finish")
	public ModelAndView finished(@RequestParam("orderId") String orderId,
	                           Map<String,Object> map){
		try{
			OrderDTO orderDTO = orderService.findOne(orderId);
			orderService.finish(orderDTO);
		}catch(Exception e){
			log.error("【卖家端完成订单】发生异常{}",e);
			map.put("msg",e.getMessage());
			map.put("url","/seller/order/list");
			return new ModelAndView("common/error",map);
		}

		map.put("msg", ResultEnum.ORDER_CANCLE_SUCCESS.getMessage());
		map.put("url","/seller/order/list");
		return new ModelAndView("common/success",map);
	}

	/**
	 * 订单详情
	 * @param orderId 订单Id
	 * @param map 返回结果
	 * @return
	 */
	@GetMapping("/detail")
	public ModelAndView detail(@RequestParam("orderId") String orderId,
	                           Map<String, Object> map) {

		OrderDTO orderDTO = new OrderDTO();
		try {
			orderDTO = orderService.findOne(orderId);
		}catch (SellException e) {
			log.error("【卖家端查询订单详情】发生异常{}", e);
			map.put("msg", e.getMessage());
			map.put("url", "/seller/order/list");
			return new ModelAndView("common/error", map);
		}

		map.put("orderDTO", orderDTO);
		return new ModelAndView("order/detail", map);
	}
}
