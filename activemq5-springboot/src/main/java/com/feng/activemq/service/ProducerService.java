package com.feng.activemq.service;


import javax.jms.Destination;

/**
 * （消息）生产者服务
 * @author
 * @date 2020/03/09
 */
public interface ProducerService {

    /**
     * 指定消息队列，以及对应的消息
     * @param destination 目标通道
     * @param message 消息
     */
    void sendMessage(Destination destination, final String message);

    /**
     * 使用默认消息队列，发送消息
     * @param message 消息
     */
    void sendMessage(final String message);
}
