package com.feng.rabbitmq.receiver;


import com.feng.rabbitmq.config.RabbitConfig;
import com.feng.rabbitmq.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutReceiver {

    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE1)
    public void receiveTopic1(User user) {
        System.err.println("【receiveFanout1监听到消息】" + user);
    }

    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE2)
    public void receiveTopic2(User user) {
        System.err.println("【receiveFanout2监听到消息】" + user);
    }
}
