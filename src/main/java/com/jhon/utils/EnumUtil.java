package com.jhon.utils;

import com.jhon.enums.CodeEnum;

/**
 * <p>功能描述</br> 枚举工具类 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName EnumUtil
 * @date 2017/9/14 11:49
 */
public class EnumUtil {
	public static <T extends CodeEnum> T getByCode(Integer code,Class<T> enumClass){
		for(T each: enumClass.getEnumConstants()){
			if(code.equals(each.getCode())){
				return each;
			}
		}
		return null;
	}
}
