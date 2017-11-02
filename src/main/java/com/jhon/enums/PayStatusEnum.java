package com.jhon.enums;

import lombok.Getter;

/**
 * <p>功能描述</br> 支付状态枚举</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName PayStatusEnum
 * @date 2017/9/14 10:21
 */
@Getter
public enum PayStatusEnum implements CodeEnum{
	WAIT(0,"等待支付"),
	SUCCESS(1,"支付成功"),
	;
	private Integer code;

	private String message;

	PayStatusEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
