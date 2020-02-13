package com.feng.rocket.consumer.listener;

import com.feng.rocket.rocketbean.common.RocketMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RocketMQMessageListener(topic = RocketMQConfig.TEST_TOPIC_1, consumerGroup = RocketMQConfig.CONSUMER_GROUP_1)
public class MyConsumer1 implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        log.info("消费者消费消息开始-----------");
        //JSONObject jsonObject =  JSON.parseObject(message);
        //OrderPaidEvent orderPaidEvent = JSONObject.parseObject(message, OrderPaidEvent.class);
        log.info("消费者开始接收第一个消息received message: {}", s);
    }
}
