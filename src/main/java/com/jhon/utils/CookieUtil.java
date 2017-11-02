package com.jhon.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>功能描述</br> Cookie的工具类 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName CookieUtil
 * @date 2017/9/14 21:24
 */
public class CookieUtil {

	/**
	 * 设置Cookie
	 * @param response
	 * @param name 键
	 * @param value 值
	 * @param maxAge 生命周期
	 */
	public static void set(HttpServletResponse response, String name,  String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	/**
	 * 获取Cookie中的值
	 * @param request 请求对象
	 * @param name cookie中的键
	 * @return
	 */
	public static Cookie get(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = readCookieMap(request);
		if (cookieMap.containsKey(name)) {
			return cookieMap.get(name);
		}
		return null;
	}

	/**
	 * 获取所有的Cookie
	 *
	 * @param request 请求对象
	 * @return
	 */
	public static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<>();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
}
