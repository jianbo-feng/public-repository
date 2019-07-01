package com.feng.rocket.producer.config;

import com.feng.rocket.rocketbean.common.RocketMQConfig;
import com.feng.rocket.rocketbean.entity.OrderPaidEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class RocketMQProducer implements CommandLineRunner {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void run(String... args) throws Exception {
        log.info("-----------生产者开始生产消息-----------");
        String msg = "Hello, World!";
        rocketMQTemplate.convertAndSend( RocketMQConfig.TEST_TOPIC_1, msg);
        log.info("生产者生产第一个消息完成: 主题:{}, 内容:{}", RocketMQConfig.TEST_TOPIC_1, msg);
        OrderPaidEvent orderPaidEvent = new OrderPaidEvent("orderId-0001", 88);
        rocketMQTemplate.convertAndSend( RocketMQConfig.TEST_TOPIC_2, orderPaidEvent);
        log.info("生产者生产第二个消息完成: 主题:{}, 内容:{}", RocketMQConfig.TEST_TOPIC_2, orderPaidEvent);
        log.info("-----------生产者生产消息结束-----------");
    }
}
