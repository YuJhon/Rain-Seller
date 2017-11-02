package com.jhon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * <p>功能描述</br> WebSocket配置 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName WebSocketConfig
 * @date 2017/9/15 17:24
 */
@Component
public class WebSocketConfig {

	@Bean
	public ServerEndpointExporter serverEndpointExporter(){
		return new ServerEndpointExporter();
	}
}
