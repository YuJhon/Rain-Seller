package com.jhon.service.impl;

import com.jhon.converter.OrderDO2OrderDTOConverter;
import com.jhon.dao.OrderDAO;
import com.jhon.dao.OrderDetailDAO;
import com.jhon.dto.CartDTO;
import com.jhon.dto.OrderDTO;
import com.jhon.entity.OrderDO;
import com.jhon.entity.OrderDetailDO;
import com.jhon.entity.ProductInfoDO;
import com.jhon.enums.OrderStatusEnum;
import com.jhon.enums.PayStatusEnum;
import com.jhon.enums.ResultEnum;
import com.jhon.exception.SellException;
import com.jhon.service.*;
import com.jhon.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>功能描述</br> 订单业务逻辑实现类 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName OrderServiceImpl
 * @date 2017/9/14 15:50
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private OrderDetailDAO orderDetailDAO;

	@Autowired
	private ProductInfoService productInfoService;

	@Autowired
	private PayService payService;

	@Autowired
	private PushMessageService pushMessageService;

	@Autowired
	private WebSocket webSocket;

	@Override
	public OrderDTO create(OrderDTO orderDTO) {

		String orderId = KeyUtil.getUniqueKey();
		BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

		/** 1.查询商品（数量、价格）**/
		for (OrderDetailDO orderDetail : orderDTO.getOrderDetailList()) {
			ProductInfoDO productInfo = productInfoService.findOne(orderDetail.getProductId());
			if (productInfo == null) {
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}

			/** 计算订单的总金额 **/
			orderAmount = productInfo.getProductPrice()
					.multiply(new BigDecimal(orderDetail.getProductQuantity()))
					.add(orderAmount);

			/** 订单详情入库 **/
			orderDetail.setOrderId(orderId);
			orderDetail.setDetailId(KeyUtil.getUniqueKey());
			BeanUtils.copyProperties(productInfo, orderDetail);
			orderDetailDAO.save(orderDetail);
		}

		/** 2.订单信息入库 **/
		OrderDO orderDO = new OrderDO();
		orderDTO.setOrderId(orderId);
		BeanUtils.copyProperties(orderDTO, orderDO);
		orderDO.setOrderAmount(orderAmount);
		orderDO.setOrderStatus(OrderStatusEnum.NEW.getCode());
		orderDO.setPayStatus(PayStatusEnum.WAIT.getCode());
		orderDAO.save(orderDO);

		/** 3.扣库存 **/
		List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
				new CartDTO(e.getProductId(), e.getProductQuantity())
		).collect(Collectors.toList());

		productInfoService.decreaseStock(cartDTOList);

		/** WebSocket 推送消息 TODO  **/
		webSocket.sendMessage(orderDTO.getOrderId());

		return orderDTO;
	}

	@Override
	public OrderDTO findOne(String orderId) {
		OrderDO orderDO = orderDAO.findOne(orderId);
		if (orderDO == null) {
			throw new SellException(ResultEnum.ORDER_NOT_EXIST);
		}

		List<OrderDetailDO> orderDetailList = orderDetailDAO.findByOrderId(orderId);
		if (CollectionUtils.isEmpty(orderDetailList)) {
			throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
		}
		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(orderDO, orderDTO);
		orderDTO.setOrderDetailList(orderDetailList);

		return orderDTO;
	}

	@Override
	public Page<OrderDTO> findList(String buyerOpenId, Pageable pageable) {
		/** 分页查询记录 **/
		Page<OrderDO> orderDOPage = orderDAO.findByBuyerOpenId(buyerOpenId, pageable);
		/** 批量转换 **/
		List<OrderDTO> orderDTOList = OrderDO2OrderDTOConverter.convert(orderDOPage.getContent());
		/** 返回分页的数据对象 **/
		return new PageImpl<OrderDTO>(orderDTOList, pageable, orderDOPage.getTotalElements());
	}

	@Override
	@Transactional
	public OrderDTO cancle(OrderDTO orderDTO) {
		OrderDO orderDO = new OrderDO();

		/** 判断订单的状态 **/
		if (!OrderStatusEnum.NEW.getCode().equals(orderDTO.getOrderStatus())) {
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}

		/** 修改订单的状态 **/
		orderDTO.setOrderStatus(OrderStatusEnum.CANCLE.getCode());
		BeanUtils.copyProperties(orderDTO, orderDO);
		OrderDO updateResult = orderDAO.save(orderDO);
		if (updateResult == null) {
			log.error("【取消订单】订单更新失败，OrderDo={}", orderDO);
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}

		/** 库存的修改 **/
		List<CartDTO> cartDTOList = orderDTO.getOrderDetailList()
				.stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
				.collect(Collectors.toList());
		productInfoService.increaseStock(cartDTOList);

		/** 如果已经支付的话，需要退款 **/
		if (PayStatusEnum.SUCCESS.getCode().equals(orderDTO.getPayStatus())) {
			payService.refund(orderDTO);
		}
		return orderDTO;
	}

	@Override
	@Transactional
	public OrderDTO finish(OrderDTO orderDTO) {
		/** 判断订单状态 **/
		if (!OrderStatusEnum.NEW.getCode().equals(orderDTO.getOrderStatus())) {
			log.error("【完成订单】订单状态不正确，OrderId={},OrderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}

		/** 修改订单的状态 **/
		orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
		OrderDO orderDO = new OrderDO();
		BeanUtils.copyProperties(orderDTO, orderDO);
		OrderDO updateOrder = orderDAO.save(orderDO);
		if (updateOrder == null) {
			log.error("【完成订单】更新失败，orderDo={}", orderDO);
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}

		/** 推送微信模板消息 TODO **/
		pushMessageService.orderStatus(orderDTO);
		return orderDTO;
	}

	@Override
	@Transactional
	public OrderDTO paid(OrderDTO orderDTO) {
		/** 判断订单状态 **/
		if (!OrderStatusEnum.NEW.getCode().equals(orderDTO.getOrderStatus())) {
			log.error("【订单支付完成】订单状态不正确，orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}

		/** 判断订单支付状态 **/
		if (!PayStatusEnum.WAIT.getCode().equals(orderDTO.getPayStatus())) {
			log.error("【订单支付完成】订单支付状态不正确，orderDTO={}", orderDTO);
			throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
		}

		/** 修改订单的支付状态 **/
		orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
		OrderDO orderDO = new OrderDO();
		BeanUtils.copyProperties(orderDTO, orderDO);
		OrderDO updateOrder = orderDAO.save(orderDO);
		if (updateOrder == null) {
			log.error("【订单支付完成】更新失败，OrderDo={}", orderDO);
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}

		return orderDTO;

	}

	@Override
	public Page<OrderDTO> findList(Pageable pageable) {
		/** 分页查询记录 **/
		Page<OrderDO> orderDOPage = orderDAO.findAll(pageable);
		/** 批量转换 **/
		List<OrderDTO> orderDTOList = OrderDO2OrderDTOConverter.convert(orderDOPage.getContent());
		/** 返回分页的数据对象 **/
		return new PageImpl<OrderDTO>(orderDTOList, pageable, orderDOPage.getTotalElements());
	}
}
