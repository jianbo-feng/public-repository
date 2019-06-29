package com.feng.activemq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

@Configuration
@EnableJms
public class JSMListeners {

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
