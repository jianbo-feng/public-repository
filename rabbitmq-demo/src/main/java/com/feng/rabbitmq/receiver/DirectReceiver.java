package com.feng.rabbitmq.receiver;

import com.feng.rabbitmq.config.RabbitConfig;
import com.feng.rabbitmq.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectReceiver {
    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitConfig.DIRECT_QUEUE1)
    public void receiveDirect1(User user) {

        System.err.println("【receiveDirect1监听到消息】" + user);
    }

    @RabbitListener(queues = RabbitConfig.DIRECT_QUEUE1)
    public void receiveDirect2(User user) {

        System.err.println("【receiveDirect2监听到消息】" + user);
    }
}
