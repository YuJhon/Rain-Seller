package com.jhon.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * <p>功能描述</br> 订单详情实体 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName OrderDetailDO
 * @date 2017/9/14 11:17
 */
@Data
@Entity
@Table(name = "t_order_detail")
public class OrderDetailDO {

	@Id
	private String detailId;

	/** 订单id. */
	private String orderId;

	/** 商品id. */
	private String productId;

	/** 商品名称. */
	private String productName;

	/** 商品单价. */
	private BigDecimal productPrice;

	/** 商品数量. */
	private Integer productQuantity;

	/** 商品小图. */
	private String productIcon;


}
