package com.jhon.controller;

import com.jhon.config.ProjectUrlConfig;
import com.jhon.enums.ResultEnum;
import com.jhon.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

/**
 * <p>功能描述</br> 微信SDK授权操作 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName WechatController
 * @date 2017/9/16 12:02
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {

	@Autowired
	private WxMpService wxMpService;

	@Autowired
	private WxMpService wxOpenService;

	@Autowired
	private ProjectUrlConfig projectUrlConfig;

	/**
	 * 使用Base方式
	 * @param returnUrl
	 * @return
	 */
	@GetMapping("/authorize")
	public String authorize(@RequestParam("returnUrl") String returnUrl) {
		String url = projectUrlConfig.wechatMpAuthorize + "/wechat/userinfo";
		String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_USER_INFO, URLEncoder.encode(returnUrl));
		return "redirect:" + redirectUrl;
	}

	@GetMapping("/userinfo")
	public String userInfo(@RequestParam("code") String code,
	                       @RequestParam("state") String returnUrl){
		WxMpOAuth2AccessToken wxMpOAuth2AccessToken = null;
		String openId = "";
		try{
			wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
			openId = wxMpOAuth2AccessToken.getOpenId();
		}catch(WxErrorException e){
			e.printStackTrace();
			log.error("【微信网页授权】{}",e);
			throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(),e.getError().getErrorMsg());
		}
		return "redirect:" + returnUrl + "?openid=" + openId;
	}


	@GetMapping("/qrAuthorize")
	public String qrAuthorize(@RequestParam("returnUrl") String returnUrl) {
		String url = projectUrlConfig.getWechatOpenAuthorize() + "/sell/wechat/qrUserInfo";
		String redirectUrl = wxOpenService.buildQrConnectUrl(url, WxConsts.QRCONNECT_SCOPE_SNSAPI_LOGIN, URLEncoder.encode(returnUrl));
		return "redirect:" + redirectUrl;
	}

	@GetMapping("/qrUserInfo")
	public String qrUserInfo(@RequestParam("code") String code,
	                         @RequestParam("state") String returnUrl) {
		WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
		try {
			wxMpOAuth2AccessToken = wxOpenService.oauth2getAccessToken(code);
		} catch (WxErrorException e) {
			log.error("【微信网页授权】{}", e);
			throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
		}
		log.info("wxMpOAuth2AccessToken={}", wxMpOAuth2AccessToken);
		String openId = wxMpOAuth2AccessToken.getOpenId();

		return "redirect:" + returnUrl + "?openid=" + openId;
	}

}
