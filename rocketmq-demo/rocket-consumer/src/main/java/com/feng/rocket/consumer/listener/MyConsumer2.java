package com.feng.rocket.consumer.listener;


import com.feng.rocket.rocketbean.common.RocketMQConfig;
import com.feng.rocket.rocketbean.entity.OrderPaidEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RocketMQMessageListener(topic = RocketMQConfig.TEST_TOPIC_2, consumerGroup = RocketMQConfig.CONSUMER_GROUP_2)
public class MyConsumer2 implements RocketMQListener<OrderPaidEvent> {

    @Override
    public void onMessage(OrderPaidEvent orderPaidEvent) {
        log.info("消费者开始接收第二个消息received orderPaidEvent: {}", orderPaidEvent);
        log.info("消费者消费消息结束-----------");
    }
}
