package com.jhon.VO;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>功能描述</br> 返回界面渲染的对象 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ResultVO
 * @date 2017/9/14 17:37
 */
@Data
public class ResultVO<T> implements Serializable {
	private static final long serialVersionUID = -5046934764093736363L;

	/** 错误码 **/
	private Integer code;

	/** 提示信息 **/
	private String msg;

	/** 具体内容 **/
	private T data;
}
