package com.jhon.converter;

import com.jhon.dto.OrderDTO;
import com.jhon.entity.OrderDO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>功能描述</br> 订单实体对象转换成订单值对象 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName OrderDO2OrderDTOConverter
 * @date 2017/9/14 20:20
 */
public class OrderDO2OrderDTOConverter {

	/**
	 * 订单实体转换成订单值对象
	 *
	 * @param orderDO
	 * @return
	 */
	public static OrderDTO convert(OrderDO orderDO) {
		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(orderDO, orderDTO);
		return orderDTO;
	}

	/**
	 * 批量将订单实体装换成订单值对象
	 *
	 * @param orderList
	 * @return
	 */
	public static List<OrderDTO> convert(List<OrderDO> orderList) {
		return orderList.stream().map(
				e -> convert(e)
		).collect(Collectors.toList());
	}
}
