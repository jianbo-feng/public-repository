package com.feng.activemq.test.pub;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息发布者发送
 * 消费者只能接收到注册之后，发送端发送的消息; 也就是注册之前的消息是接收不到的
 * 发送端发送的消息会分发给所有注册的消费者
 * 注册发送端的关键在于session.createTopic("Topic名称")中的Topic名称
 * @see <a href="https://www.jianshu.com/p/79607774c3cc">参考</a>
 */
public class PubReceive {
    public static void main(String[] args) throws JMSException {
        //创建链接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        //创建连接
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //创建一个会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic("JOB01");
        MessageConsumer messageConsumer = session.createConsumer(destination);
        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message arg0) {
                MapMessage map = (MapMessage)arg0;
                try {
                    String shli = map.getString("name");
                    double price = map.getDouble("price");
                    boolean up = map.getBoolean("up");
                    System.out.println(shli + "----" + price +"------"+up);
                } catch (JMSException e) {
                    e.printStackTrace();
                }

            }
        });

        MessageConsumer messageConsumer1 = session.createConsumer(destination);
        messageConsumer1.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message arg0) {
                MapMessage map = (MapMessage)arg0;
                try {
                    String shli = map.getString("name");
                    double price = map.getDouble("price");
                    boolean up = map.getBoolean("up");
                    System.out.println(shli + "-1-1-1-" + price +"-1--1-1--"+up);
                } catch (JMSException e) {
                    e.printStackTrace();
                }

            }
        });
        //session.close();
        //connection.close();
    }

}
