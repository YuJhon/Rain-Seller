package com.jhon.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * <p>功能描述</br> json转换的工具类 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName JsonUtil
 * @date 2017/9/14 21:22
 */
public class JsonUtil {

	/**
	 * 对象转换成Json字符串
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();
		return gson.toJson(obj);
	}
}
