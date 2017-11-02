package com.jhon.enums;

import lombok.Getter;

/**
 * <p>功能描述</br> 商品信息 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ProductStatusEnum
 * @date 2017/9/14 11:28
 */
@Getter
public enum ProductStatusEnum implements CodeEnum{
	UP(0,"在架"),
	DOWN(1,"下架"),
	;

	private Integer code;

	private String message;

	ProductStatusEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
