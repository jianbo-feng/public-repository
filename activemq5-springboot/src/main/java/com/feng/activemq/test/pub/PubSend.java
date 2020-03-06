package com.feng.activemq.test.pub;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息发布者发送
 * 消费者只能接收到注册之后，发送端发送的消息; 也就是注册之前的消息是接收不到的
 * 发送端发送的消息会分发给所有注册的消费者
 * 注册发送端的关键在于session.createTopic("Topic名称")中的Topic名称
 * @see <a href="https://www.jianshu.com/p/79607774c3cc">参考</a>
 */
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
