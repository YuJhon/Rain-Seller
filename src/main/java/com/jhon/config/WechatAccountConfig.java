package com.jhon.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>功能描述</br> 微信账号配置 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName WechatAccountConfig
 * @date 2017/9/15 16:30
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

	/** 公众平台Id **/
	private String mpAppId;

	/** 公众平台密钥 **/
	private String mpAppSecret;

	/** 开放平台Id **/
	private String openAppId;

	/** 开放平台秘钥 **/
	private String openAppSecret;

	/** 商户名 **/
	private String mchId;

	/** 商户秘钥 **/
	private String mchKey;

	/** 商户证书路径 **/
	private String keyPath;

	/** 微信支付异步通知地址 **/
	private String notifyUrl;

	/** 微信模板Id **/
	private Map<String,String> templateId;

}
