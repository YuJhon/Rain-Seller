package com.jhon.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>功能描述</br> 项目的url路径配置 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ProjectUrlConfig
 * @date 2017/9/15 13:48
 */
@Data
@ConfigurationProperties(prefix = "projectUrl")
@Component
public class ProjectUrlConfig {

	/** 微信公众平台授权url **/
	public String wechatMpAuthorize;

	/** 微信开放平台授权url **/
	public String wechatOpenAuthorize;

	/** 项目系统url **/
	public String sell;
}
