package com.feng.activemq.test.all;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 同步监听
 */
public class ActivemqQueueConsumerAsyn implements MessageListener{
    private String name = "";
    private Connection connection = null;
    private Session session = null;
    private MessageConsumer consumer = null;
    ActivemqQueueConsumerAsyn(String name){
        this.name=name;
    }

    public  void initialize() throws JMSException
    {ConnectionFactory connectFactory = new ActiveMQConnectionFactory(
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
            // 开始监听
            consumer.setMessageListener(this);  //(异步接收)

        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message arg0) {
        // TODO Auto-generated method stub
        try{
            if(arg0 instanceof TextMessage)
            {
                TextMessage txtMsg = (TextMessage) arg0;
                System.out.println("consumer("+name+")异步 recive:"+txtMsg.getText());
                Thread.sleep(500);
            }
        }catch(Exception e)
        {
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
