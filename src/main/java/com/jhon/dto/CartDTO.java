package com.jhon.dto;

import lombok.Data;

/**
 * <p>功能描述</br> 购物车值对象</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName CartDTO
 * @date 2017/9/14 16:56
 */
@Data
public class CartDTO {

	/** 商品Id **/
	private String productId;

	/** 数量 **/
	private Integer productQuantity;

	public CartDTO(String productId, Integer productQuantity) {
		this.productId = productId;
		this.productQuantity = productQuantity;
	}
}
