package com.jhon.utils;

import java.util.Random;

/**
 * <p>功能描述</br> 主键生成工具类 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName KeyUtil
 * @date 2017/9/14 16:01
 */
public class KeyUtil {

	/**
	 * 生成唯一的key值
	 * @return
	 */
	public static synchronized String getUniqueKey(){
		Random random = new Random();
		Integer number = random.nextInt(9000000) + 1000000;
		return System.currentTimeMillis() + String.valueOf(number);
	}
}
