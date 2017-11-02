package com.jhon.exception;

import com.jhon.enums.ResultEnum;

/**
 * <p>功能描述</br> 自定义异常 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName SellException
 * @date 2017/9/14 16:14
 */
public class SellException extends RuntimeException {

	private Integer code;

	public SellException(ResultEnum resultEnum){
		super(resultEnum.getMessage());
		this.code = resultEnum.getCode();
	}

	public SellException(Integer code,String message){
		super(message);
		this.code = code;
	}
}
