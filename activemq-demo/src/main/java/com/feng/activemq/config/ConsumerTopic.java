package com.feng.activemq.config;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 消息主题模式
 */
@Component
public class ConsumerTopic {

    // 使用JmsListener配置（主题模式）消费者监听，其中text是接收到的消息
    @JmsListener(destination = "jms-topic")
    public void receiveQueue(String text) {
        System.err.println("ConsumerTopic收到:" + text);
    }
}
