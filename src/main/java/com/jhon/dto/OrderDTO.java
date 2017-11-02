package com.jhon.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jhon.entity.OrderDetailDO;
import com.jhon.enums.OrderStatusEnum;
import com.jhon.enums.PayStatusEnum;
import com.jhon.utils.EnumUtil;
import com.jhon.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>功能描述</br> 订单的数据转换 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName OrderDTO
 * @date 2017/9/14 14:34
 */
@Data
public class OrderDTO {

	/** 订单Id **/
	private String orderId;

	/** 买家名字 **/
	private String buyerName;

	/** 买家手机号 **/
	private String buyerPhone;

	/** 买家地址 **/
	private String buyerAddress;

	/** 买家微信OpenId **/
	private String buyerOpenId;

	/** 订单总金额 **/
	private BigDecimal orderAmount;

	/** 订单状态,默认为0 新下单 **/
	private Integer orderStatus;

	/** 支付状态,默认为0未支付 **/
	private Integer payStatus;

	/** 创建时间 **/
	@JsonSerialize(using = Date2LongSerializer.class)
	private Date createTime;

	/** 更新时间 **/
	@JsonSerialize(using = Date2LongSerializer.class)
	private Date updateTime;

	/** 订单详情列表 **/
	List<OrderDetailDO> orderDetailList;

	@JsonIgnore
	public OrderStatusEnum getOrderStatusEnum(){
		return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
	}

	@JsonIgnore
	public PayStatusEnum getPayStatusEnum(){
		return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
	}
}
