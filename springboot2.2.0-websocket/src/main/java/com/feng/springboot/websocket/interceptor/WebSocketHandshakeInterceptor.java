package com.feng.springboot.websocket.interceptor;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.feng.springboot.websocket.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * WebSocket握手拦截器
 * @author
 *
 */
public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {

    private static Logger logger = LoggerFactory.getLogger(HandshakeInterceptor.class);

    /**
     * 在握手之后
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object
                > attributes) throws Exception {
    	logger.debug("beforeHandshake start.....");
    	logger.debug(request.getClass().getName());
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            if (session != null) {
                //使用userName区分WebSocketHandler，以便定向发送消息
                String userName = (String) session.getAttribute(Constants.SESSION_USERNAME);
                logger.info(userName+" login");
                attributes.put(Constants.WEBSOCKET_USERNAME,userName);

                /*使用websocketSessionKey区分WebSocketHandler  modify by feng*/
                String websocketSessionKey = userName + ";" + session.getId();
                attributes.put(Constants.WEBSOCKET_SESSION_KEY, websocketSessionKey);

                System.err.println("connect success");
            }else{
            	logger.debug("httpsession is null");
            }
        }
        return true;
    }

    /**
     * 在握手之前
     */
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
    }
}
