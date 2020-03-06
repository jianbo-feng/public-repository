package com.feng.activemq.test.pub;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class PubSend {
    public static void main(String[] args) throws JMSException {
        //创建链接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        //创建连接
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //创建一个会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建生产者 发送消息的人
        MessageProducer producer = session.createProducer(null);
        Destination destination = session.createTopic("JOB01");
        MapMessage message = session.createMapMessage();
        message.setString("name", "shli");
        message.setDouble("price", 1.00);
        message.setBoolean("up", true);
        producer.send(destination, message);
        producer.close();
        session.close();
        connection.close();
    }
}
