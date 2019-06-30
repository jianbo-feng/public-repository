package com.feng.activemq.service.impl;

import com.feng.activemq.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;


@Service
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    private JmsMessagingTemplate template;

    @Override
    public void sendMessage(Destination destination, String message) {
        template.convertAndSend(destination, message);
    }

    @Override
    public void sendMessage(String message) {
        template.convertAndSend(message);
    }
}
