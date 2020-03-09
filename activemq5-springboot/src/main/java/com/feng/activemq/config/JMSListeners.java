package com.feng.activemq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

/**
 * 消息监听配置
 * @author 消息监听器集
 * @date 2020/03/09
 */
@Configuration
@EnableJms
public class JMSListeners {

    /**
     * 点对点
     * @return
     */
    @Bean
    public ActiveMQQueue queue() {
        return new ActiveMQQueue("jms-queue");
    }


    /**
     * 订阅/发布
     * @return
     */
    @Bean
    public ActiveMQTopic topic() {
        return new ActiveMQTopic("jms-topic");
    }
}
