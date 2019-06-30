package com.feng.rabbitmq.sender;


import com.feng.rabbitmq.config.RabbitConfig;
import com.feng.rabbitmq.entity.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DirectSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(User user) {
        this.rabbitTemplate.convertAndSend(RabbitConfig.DIRECT_EXCHANGE, "direct.feng", user);
    }
}
