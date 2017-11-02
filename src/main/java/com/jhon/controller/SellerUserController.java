package com.jhon.controller;

import com.jhon.config.ProjectUrlConfig;
import com.jhon.constant.CookieConstant;
import com.jhon.constant.RedisConstant;
import com.jhon.entity.SellerInfoDO;
import com.jhon.enums.ResultEnum;
import com.jhon.service.SellerService;
import com.jhon.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>功能描述</br> 卖家用户信息处理控制器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName SellerUserController
 * @date 2017/9/15 13:42
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {

	@Autowired
	private SellerService sellerService;

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Autowired
	private ProjectUrlConfig projectUrlConfig;

	/**
	 * 卖家登录接口
	 * @param openid 用户的openid
	 * @param response
	 * @param map
	 * @return
	 */
	@GetMapping("/login")
	public ModelAndView login(@RequestParam("openid") String openid,
	                          HttpServletResponse response,
	                          Map<String,Object> map){
		/** openid去和数据库进行匹配 **/
		SellerInfoDO sellerInfo = sellerService.findSellerInfoByOpenid(openid);
		if(sellerInfo == null){
			map.put("msg", ResultEnum.LOGIN_FAIL.getMessage());
			map.put("url","/seller/order/list");
			return new ModelAndView("common/error");
		}

		/** 设置Token到Redis **/
		String token = UUID.randomUUID().toString();
		Integer expire = RedisConstant.EXPIRE;
		redisTemplate.opsForValue()
				.set(String.format(RedisConstant.TOKEN_PREFIX,token),openid,expire, TimeUnit.SECONDS);

		/** 设置token到cookie **/
		CookieUtil.set(response, CookieConstant.TOKEN,token,expire);

		return new ModelAndView("redirect:"+projectUrlConfig.getSell() + "/seller/order/list");
	}

	/**
	 * 注销登录
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request,
	                           HttpServletResponse response,
	                           Map<String,Object> map){
		//1. 从cookie里查询
		Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
		if (cookie != null) {
			//2. 清除redis
			redisTemplate.opsForValue()
					.getOperations()
					.delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));

			//3. 清除cookie
			CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
		}

		map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
		map.put("url", "/seller/order/list");
		return new ModelAndView("common/success", map);
	}
}
