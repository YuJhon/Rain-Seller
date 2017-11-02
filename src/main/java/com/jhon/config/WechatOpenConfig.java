package com.jhon.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * <p>功能描述</br> TODO</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName WechatOpenConfig
 * @date 2017/9/15 17:01
 */
@Component
public class WechatOpenConfig {

	@Autowired
	private WechatAccountConfig accountConfig;

	@Bean
	public WxMpService wxOpenService(){
		WxMpService wxOpenService = new WxMpServiceImpl();
		wxOpenService.setWxMpConfigStorage(wxOpenConfigStorage());
		return wxOpenService;
	}

	@Bean
	public WxMpConfigStorage wxOpenConfigStorage(){
		WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
		wxMpInMemoryConfigStorage.setAppId(accountConfig.getOpenAppId());
		wxMpInMemoryConfigStorage.setSecret(accountConfig.getOpenAppSecret());
		return wxMpInMemoryConfigStorage;
	}
}
