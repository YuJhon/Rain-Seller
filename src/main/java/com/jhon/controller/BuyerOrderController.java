package com.jhon.controller;

import com.jhon.VO.ResultVO;
import com.jhon.converter.OrderForm2OrderDTOConverter;
import com.jhon.dto.OrderDTO;
import com.jhon.entity.OrderDetailDO;
import com.jhon.enums.ResultEnum;
import com.jhon.exception.SellException;
import com.jhon.form.OrderForm;
import com.jhon.service.BuyerService;
import com.jhon.service.OrderService;
import com.jhon.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>功能描述</br> 买家订单控制器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName BuyerOrderController
 * @date 2017/9/14 17:25
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private BuyerService buyerService;

	/**
	 * 创建订单
	 *
	 * @param orderForm     订单前端Form对象
	 * @param bindingResult 绑定处理的结果
	 * @return
	 */
	@PostMapping("/create")
	public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			log.error("【创建订单】参数不正确,OrderForm={}", orderForm);
			throw new SellException(ResultEnum.PARAMS_ERROR.getCode(),
					bindingResult.getFieldError().getDefaultMessage());
		}

		OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
		if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
			log.error("【创建订单】购物车不能为空");
			throw new SellException(ResultEnum.CART_EMPTY);
		}

		OrderDTO createResult = orderService.create(orderDTO);

		Map<String, String> map = new HashMap<String, String>();
		map.put("orderId", createResult.getOrderId());

		return ResultVOUtil.success(map);
	}


	@GetMapping("/createWs")
	public void createDemo(){
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setBuyerName("江江");
		orderDTO.setBuyerAddress("大梅沙");
		orderDTO.setBuyerPhone("15001288615");
		orderDTO.setBuyerOpenId("oJYDq1C0-1w_YMygLupVycmwykN8");

		//购物车
		List<OrderDetailDO> orderDetailList = new ArrayList<>();
		OrderDetailDO o1 = new OrderDetailDO();
		o1.setProductId("1000000001");
		o1.setProductQuantity(1);

		OrderDetailDO o2 = new OrderDetailDO();
		o2.setProductId("1000000002");
		o2.setProductQuantity(2);

		orderDetailList.add(o1);
		orderDetailList.add(o2);

		orderDTO.setOrderDetailList(orderDetailList);

		OrderDTO result = orderService.create(orderDTO);
		log.info("【创建订单】result={}", result);
		Assert.assertNotNull(result);
	}

	/**
	 * 订单列表
	 *
	 * @param openId 微信OpenId
	 * @param page   页码
	 * @param size   每页大小
	 * @return
	 */
	@GetMapping("/list")
	public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openId,
	                                     @RequestParam(value = "page", defaultValue = "0") Integer page,
	                                     @RequestParam(value = "size", defaultValue = "10") Integer size) {

		if (StringUtils.isEmpty(openId)) {
			log.error("【查询订单列表】openid为空");
			throw new SellException(ResultEnum.PARAMS_ERROR);
		}

		PageRequest request = new PageRequest(page, size);
		Page<OrderDTO> orderDTOPage = orderService.findList(openId, request);

		return ResultVOUtil.success(orderDTOPage.getContent());
	}


	/**
	 * 查询订单详情
	 * @param openId 用户微信OpenId
	 * @param orderId 订单Id
	 * @return
	 */
	@GetMapping("/detail")
	public ResultVO<OrderDTO> detail(@RequestParam("openid") String openId,
	                                 @RequestParam("orderId") String orderId){
		OrderDTO orderDTO = buyerService.findOrderOne(openId,orderId);

		return ResultVOUtil.success(orderDTO);
	}

	/**
	 * 取消订单
	 * @param openId 用户微信OpenId
	 * @param orderId 订单Id
	 * @return
	 */
	@PostMapping("/cancle")
	public ResultVO<?> cancle(@RequestParam("openid") String openId,
	                       @RequestParam("orderId") String orderId){

		buyerService.cancleOrder(openId,orderId);
		return ResultVOUtil.success();
	}

}
