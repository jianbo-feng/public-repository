package com.feng.activemq.controller;

import com.alibaba.fastjson.JSON;
import com.feng.activemq.entity.User;
import com.feng.activemq.service.ProducerService;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/jms")
public class JmsController {

    @Autowired
    private ActiveMQQueue queue;

    @Autowired
    private ActiveMQTopic topic;

    @Autowired
    private ProducerService producerService;

    /**
     * 队列（模式）发送消息
     * @param msg
     */
    @GetMapping("/activemq/queue")
    public void sendQueue(String msg) {
        producerService.sendMessage(queue, msg);
    }


    /**
     * 主题（模式）发送消息
     * @param msg
     */
    @GetMapping("/activemq/topic")
    public void sendTopic(String msg) {
        producerService.sendMessage(this.topic, msg);
    }

    /**
     * 发送JSON<br/>
     * 若model为queue,需设置spring.jml.pub-sub-domain=false；若为topic, 则为spring.jml.pub-sub-domain=true
     * @param model 模式(默认：queue)
     * @return
     */
    @GetMapping("/activemq/json")
    public Map<String, Object> sendJson(@RequestParam(defaultValue = "queue") String model) {
        Map<String, Object> ret = new HashMap<>();
        model = model.toLowerCase();
        ret.put("model", model);
        User user = new User(UUID.randomUUID().toString(), "张三", 22);
        String json = JSON.toJSONString(user);
        ret.put("user", json);
        if("queue".equals(model)) {
            producerService.sendMessage(queue, json);
        }
        else if("topic".equals(model)) {
            producerService.sendMessage(topic, json);
        }
        return ret;
    }
}
