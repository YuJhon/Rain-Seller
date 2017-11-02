package com.jhon.enums;

import lombok.Getter;

/**
 * <p>功能描述</br> 订单状态的枚举类</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName OrderStatusEnum
 * @date 2017/9/14 10:19
 */
@Getter
public enum OrderStatusEnum implements CodeEnum {
	NEW(0,"新订单"),
	FINISHED(1,"完成"),
	CANCLE(2,"已取消");

	private Integer code;

	private String message;

	OrderStatusEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
