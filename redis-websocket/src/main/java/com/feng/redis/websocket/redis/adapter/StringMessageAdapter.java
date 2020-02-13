package com.feng.redis.websocket.redis.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Component
public class StringMessageAdapter {

    private final static Logger logger = LoggerFactory.getLogger(StringMessageAdapter.class);

    public void handle(String message){
        logger.info("StringMessageAdapter:handle(message):"+message);
    }
}
