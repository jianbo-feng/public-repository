package com.zeshan.kafkaconsumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zeshan.kafka.bean.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class Consumer {

    private static Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(id = "id01",topics = "demo")
    public void listen(ConsumerRecord<?,?> record){
        logger.info("主题:{} 内容：{}",record.topic(),record.value());
    }

    /**
     * 监听符合topicPattern = “topic.*”的所有topic的消息
     * @param record
     * @param topic
     */
    @KafkaListener(id = "id02", topicPattern = "topic.*")
    public void listenTopic(ConsumerRecord<?, ?> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if(kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            logger.info("record ==== topic: " + topic + "," + record);
            logger.info("message ==== topic: " + topic + "," + message);
            Object value = record.value();
            if(value instanceof JSONObject) {
                System.err.println("\t value (JSONObject) => " + JSON.toJSONString(value));
            }
            else if(value instanceof JSONArray) {
                System.err.println("\t value (JSONArray) => " + JSON.toJSONString(value));
            }
            else {
                System.err.println("\t value => " + value);
            }
            JSONObject jsonObject = JSON.parseObject(value.toString());
            User user = null;
            if(jsonObject.containsKey("user")) {
                user = jsonObject.getObject("user", User.class);
            }
            System.err.println("user => " + user.toString());
        }
    }

}
