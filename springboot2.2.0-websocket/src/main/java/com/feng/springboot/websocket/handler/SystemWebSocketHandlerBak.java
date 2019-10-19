package com.feng.springboot.websocket.handler;

import java.io.IOException;
import java.util.ArrayList;

import com.feng.springboot.websocket.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class SystemWebSocketHandlerBak implements WebSocketHandler {
	private static final Logger logger;

    private static final ArrayList<WebSocketSession> users;

    static {
        users = new ArrayList<>();
        logger = LoggerFactory.getLogger(SystemWebSocketHandler.class);
    }
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus arg1)
			throws Exception {
		logger.debug("websocket connection closed......");
        users.remove(session);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		String userName = (String) session.getAttributes().get(Constants.WEBSOCKET_USERNAME);
		logger.debug(session.getAttributes().toString());
		logger.debug(userName + " connect to the websocket success......");
        users.add(session);
        if(userName!= null){
            //查询未读消息
//            int count = webSocketService.getUnReadNews((String) session.getAttributes().get(Constants.WEBSOCKET_USERNAME));
        	sendMessageToUsers(new TextMessage(userName + " join"));
            session.sendMessage(new TextMessage(+users.size() + "users in"));
        }
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message)
			throws Exception {
		sendMessageToUsers(message);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable arg1)
			throws Exception {
		if(session.isOpen()){
            session.close();
        }
        logger.error("websocket connection  error and closed......",arg1);
        users.remove(session);
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	/**
     * 给所有在线用户发送消息
     * @param message
     */
    public void sendMessageToUsers(WebSocketMessage<?> message) {
        for (WebSocketSession user : users) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                logger.error("send message to "+user.getAttributes().get(Constants.WEBSOCKET_USERNAME)+" error!",e);
            }
        }
    }

    /**
     * 给某个用户发送消息
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) {
        for (WebSocketSession user : users) {
            if (user.getAttributes().get(Constants.WEBSOCKET_USERNAME).equals(userName)) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                	logger.error("send message to "+user.getAttributes().get(Constants.WEBSOCKET_USERNAME)+" error!",e);
                }
                break;
            }
        }
    }
}
