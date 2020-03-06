package com.feng.activemq.test.p2p;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class P2pReceiver {
    public static void main(String[] args) throws JMSException, InterruptedException {
        //创建链接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        //创建连接
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //创建一个会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("JOBS.1");
        MessageConsumer messageConsumer = session.createConsumer(destination);
        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message arg0) {
                try {
                    Thread.sleep(2000);
                    System.out.println("1" + " id:" + ((ObjectMessage)arg0).getObject());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //session.close();
        //connection.close();
    }
}
