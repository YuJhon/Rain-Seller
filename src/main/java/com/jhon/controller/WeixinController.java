package com.jhon.controller;

import com.jhon.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * <p>功能描述</br> TODO</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName WeixinController
 * @date 2017/9/16 11:12
 */
@Controller
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {

	@GetMapping("/auth")
	public void auth(@RequestParam("code") String code,@RequestParam("state") String returnUrl){
		log.info("Auth .... Code={}",code);
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxbc12b25a14f84de6&secret=3cb6f2aab41036f8fef367c7b938ad41&code="+code+"&grant_type=authorization_code";
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(url,String.class);
		log.info("Response={}",result);
	}
}
