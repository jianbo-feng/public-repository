package com.feng.rocket.rocketbean.common;

/**
 * RocketMQ 配置
 */
public class RocketMQConfig {

    /**
     * 消息主题1
     */
    public final static String TEST_TOPIC_1 = "test-topic-1";

    /**
     * 消息主题2
     */
    public final static String TEST_TOPIC_2 = "test-topic-2";

    /**
     * 消费组(消息订阅组)：my-consumer_test-topic-1
     */
    public final static String CONSUMER_GROUP_1 = "my-consumer_test-topic-1";

    /**
     * 消费组(消息订阅组)：my-consumer_test-topic-2
     */
    public final static String CONSUMER_GROUP_2 = "my-consumer_test-topic-2";
}
