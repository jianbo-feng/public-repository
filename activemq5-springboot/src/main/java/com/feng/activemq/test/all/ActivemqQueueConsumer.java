package com.feng.activemq.test.all;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ActivemqQueueConsumer {
    private String name = "";


    private String subject = "TOOL.DEFAULT";

    private Destination destination = null;

    private Connection connection = null;

    private Session session = null;

    private MessageConsumer consumer = null;
    ActivemqQueueConsumer(String name){
        this.name=name;
    }

    public  void initialize() throws JMSException
    {
        ConnectionFactory connectFactory = new ActiveMQConnectionFactory(
                "tcp://localhost:61616");
        Connection connection = connectFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination =  session.createQueue("queue1");
        consumer = session.createConsumer(destination);
        connection.start();

    }

    public void recive()
    {
        try {
            initialize();
            System.out.println("Consumer("+name+"):->Begin listening...");
            int count=0;
            while(count<10)
            {
                Message message = consumer.receive(); //主动接收消息(同步)
                System.out.println("consumer recive:"+((TextMessage)message).getText());
                count++;
                System.out.println(count);

            }
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void submit() throws JMSException
    {
        session.commit();
    }
    // 关闭连接
    public void close() throws JMSException {
        System.out.println("Consumer:->Closing connection");
        if (consumer != null)
            consumer.close();
        if (session != null)
            session.close();
        if (connection != null)
            connection.close();
    }

}
