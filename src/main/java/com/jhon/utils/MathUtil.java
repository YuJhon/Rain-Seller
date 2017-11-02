package com.jhon.utils;

/**
 * <p>功能描述</br> 数学工具类 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName MathUtil
 * @date 2017/9/14 21:37
 */
public class MathUtil {

	private static final Double MONEY_RANGE = 0.01D;

	public static Boolean equals(Double dig1, Double dig2) {
		Double result = Math.abs(dig1 - dig2);
		if (result < MONEY_RANGE) {
			return true;
		}
		return false;
	}
}
