package com.feng.springboot.websocket.common;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.WebSocketSession;

/**
 * 全局变量
 * @author Administrator
 *
 */
public class Constants {

	public static final String WEBSOCKET_USERNAME = "websocket_username";
	public static final String SESSION_USERNAME = "session_username";

	/**
	 * 使用SESSION_KEY区分WebSocketHandler，以便定向发送消息
	 */
	public static final String WEBSOCKET_SESSION_KEY = "websocket_key";

	/**
	 * 用于保存用户会话信息
	 * {key:登录名或登录名加sessionid；value：用户登录会话}
	 */
	public static final ConcurrentHashMap<String,WebSocketSession> USER_SESSIONS = new ConcurrentHashMap<String,WebSocketSession>();
}
