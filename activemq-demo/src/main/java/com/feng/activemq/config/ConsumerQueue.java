package com.feng.activemq.config;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 队列模式
 */
@Component
public class ConsumerQueue {

    // 使用JmsListener配置（队列模式）消费者监听，其中text是接收到的消息
    @JmsListener(destination = "jms-queue")
    public void receiveQueue(String text) {
        System.err.println("ConsumerQueue收到:" + text);
    }
}
