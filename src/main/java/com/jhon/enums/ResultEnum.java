package com.jhon.enums;

import lombok.Getter;

/**
 * <p>功能描述</br> 返回结果的枚举 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ResultEnum
 * @date 2017/9/14 16:17
 */
@Getter
public enum ResultEnum {

	SUCCESS(0,"成功"),

	PARAMS_ERROR(1,"参数错误"),

	PRODUCT_NOT_EXIST(10,"商品不存在"),

	PRODUCT_STOCK_ERROR(11,"库存不正确"),

	ORDER_NOT_EXIST(12,"订单不存在"),

	ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),

	ORDER_STATUS_ERROR(14,"订单状态不正确"),

	ORDER_UPDATE_FAIL(15,"更新订单失败"),

	ORDER_DETAIL_EMPTY(16,"订单详情为空"),

	ORDER_PAY_STATUS_ERROR(17,"订单支付状态不正确"),

	CART_EMPTY(18,"购物车为空"),

	ORDER_OWNER_ERROR(19,"订单不属于当前用户"),

	WECHAT_MP_ERROR(20,"微信公众号账号错误"),

	WXPAY_NOTIFY_MONEY_VERIFY_ERROR(21,"微信支付异步通知金额校验不通过"),

	ORDER_CANCLE_SUCCESS(22,"订单取消成功"),

	ORDER_FINISH_SUCCESS(23,"订单完成成功"),

	PRODUCT_STATUS_ERROR(24,"商品状态不正确"),

	LOGIN_FAIL(25,"登录失败，登录信息不正确"),

	LOGOUT_SUCCESS(26,"注销成功")
	;

	private Integer code;

	private String message;

	ResultEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
