package com.jhon.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jhon.dao.OrderDetailDAO;
import com.jhon.dto.OrderDTO;
import com.jhon.entity.OrderDetailDO;
import com.jhon.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>功能描述</br> TODO</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName OrderForm2OrderDTOConverter
 * @date 2017/9/14 17:56
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

	/**
	 * 订单表单转换为订单值对象
	 * @param orderForm
	 * @return
	 */
	public static OrderDTO convert(OrderForm orderForm){
		Gson gson = new Gson();
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setBuyerName(orderForm.getName());
		orderDTO.setBuyerPhone(orderForm.getPhone());
		orderDTO.setBuyerAddress(orderForm.getAddress());
		orderDTO.setBuyerOpenId(orderForm.getOpenId());

		List<OrderDetailDO> orderDetailList = new ArrayList<OrderDetailDO>();
		try{
			orderDetailList = gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetailDO>>(){
			}.getType());
		}catch(Exception e){
		    e.printStackTrace();
		}
		orderDTO.setOrderDetailList(orderDetailList);
		return orderDTO;
	}
}
