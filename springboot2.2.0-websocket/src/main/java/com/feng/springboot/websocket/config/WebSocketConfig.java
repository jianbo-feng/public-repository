package com.feng.springboot.websocket.config;

import com.feng.springboot.websocket.handler.SystemWebSocketHandler;
import com.feng.springboot.websocket.handler.WebSocketPushHandler;
import com.feng.springboot.websocket.interceptor.MyWebSocketInterceptor;
import com.feng.springboot.websocket.interceptor.WebSocketHandshakeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


/**
 * WebMvc适配器配置
 * @author
 */
/*@Configuration
@EnableWebMvc
@EnableWebSocket*/
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	/**
	 * 注册WebSocket握手方式
	 */
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		/**
		 * registry.addHandler(systemWebSocketHandler(),"/server").addInterceptors(new WebSocketHandshakeInterceptor()).setAllowedOrigins("*");
		 * setAllowedOrigins(“*”)中的*代表合法的请求域名,该方法接受一个可变数组作为参数,一定要配置,不然会请求时会出现403
		 */
		registry.addHandler(systemWebSocketHandler(),"/server").addInterceptors(new WebSocketHandshakeInterceptor()).setAllowedOrigins("*");
        registry.addHandler(systemWebSocketHandler(), "/sockjs/server").addInterceptors(new WebSocketHandshakeInterceptor()).setAllowedOrigins("*").withSockJS();

		registry.addHandler(wechatSocketHandler(),"/wechat").addInterceptors(new MyWebSocketInterceptor()).setAllowedOrigins("*");
        registry.addHandler(wechatSocketHandler(), "/sockjs/wechat").addInterceptors(new MyWebSocketInterceptor()).setAllowedOrigins("*").withSockJS();
	}

	@Bean
    public WebSocketHandler systemWebSocketHandler(){
        return new SystemWebSocketHandler();
    }

	@Bean
	public WebSocketPushHandler wechatSocketHandler() {
		return new WebSocketPushHandler();
	}
}
