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

/**
 * P2P点对点：消息接收
 * p2p比较简单，一方发送消息，一方接收消息。相互通信的双方是通过一个类似于队列的方式来进行交流。
 * 而在p2p里一个queue只有一个发送者和一个接收者。queue之间是通过名字区别的
 * @author
 * @date 2020/03/06
 * @see <a href="https://www.jianshu.com/p/79607774c3cc">参考</a>
 */
public class P2pReceiver {
    public static void main(String[] args) throws JMSException, InterruptedException {
        //创建链接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        //创建连接
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //创建一个会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 这里主要就是通过session.createQueue("队列名称")中的队列名称来判断有那个消费者进行消费的。
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
