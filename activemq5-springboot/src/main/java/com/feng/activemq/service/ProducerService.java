package com.feng.activemq.service;


import javax.jms.Destination;

/**
 * 接口服务
 */
public interface ProducerService {

    /**
     * 指定消息队列，以及对应的消息
     * @param destination
     * @param message
     */
    void sendMessage(Destination destination, final String message);

    /**
     * 使用默认消息队列，发送消息
     * @param message
     */
    void sendMessage(final String message);
}
