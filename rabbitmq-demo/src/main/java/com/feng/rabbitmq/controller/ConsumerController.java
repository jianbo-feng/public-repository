package com.feng.rabbitmq.controller;

import com.feng.rabbitmq.entity.User;
import com.feng.rabbitmq.sender.DirectSender;
import com.feng.rabbitmq.sender.FanoutSender;
import com.feng.rabbitmq.sender.TopicSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbitmq")
public class ConsumerController {

    @Autowired
    private FanoutSender fanoutSender;
    @Autowired
    private TopicSender topicSender;
    @Autowired
    private DirectSender directSender;

    @GetMapping("fanout")
    public String fanout() {
        User user=new User();
        user.setId("1");
        user.setName("feng");
        fanoutSender.send(user);
        return "fanout test success!!!";
    }

    /**
     * TOPIC测试
     */
    @GetMapping("topic")
    public String topic() {
        User user=new User();
        user.setId("1");
        user.setName("feng");
        topicSender.send(user);
        return "topic test success!!!";
    }

    /**
     * DIRECT测试
     */
    @GetMapping("direct")
    public String direct() {
        User user=new User();
        user.setId("1");
        user.setName("feng");
        directSender.send(user);
        return "direct test success!!!";
    }

}
