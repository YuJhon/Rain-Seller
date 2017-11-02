package com.jhon.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * <p>功能描述</br> 订单表单和校验规则 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName OrderForm
 * @date 2017/9/14 17:46
 */
@Data
public class OrderForm {

	@NotEmpty(message = "姓名必填")
	private String name;

	@NotEmpty(message = "手机号必填")
	private String phone;

	@NotEmpty(message = "地址必填")
	private String address;

	@NotEmpty(message = "openid必填")
	private String openId;

	@NotEmpty(message = "购物车不能为空")
	private String items;
}
