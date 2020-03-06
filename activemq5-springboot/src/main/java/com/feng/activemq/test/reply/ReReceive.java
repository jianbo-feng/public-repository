package com.feng.activemq.test.reply;

import com.alibaba.fastjson.JSON;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Date;
import java.util.Enumeration;

/**
 * Reply(应答模式)：接收端代码
 * @author
 * @date 2020/03/06
 * @see <a href="https://www.jianshu.com/p/6027cabd6ae5">参考</a>
 */
public class ReReceive {

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(Constants.ACTIVEMQ_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        final Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        Destination adminQueue = session.createQueue(Constants.QUEUE_NAME);

        //创建回复用的生产者
        final MessageProducer replyProducer = session.createProducer(null);
        replyProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        //创建消费者对消息进行消费
        MessageConsumer consumer = session.createConsumer(adminQueue);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    TextMessage response = session.createTextMessage();
                    if (message instanceof TextMessage) {
                        TextMessage txtMsg = (TextMessage) message;
                        String messageText = txtMsg.getText();
                        System.out.println("收到了来自ReSend的消息------"+ messageText);
                        response.setText("回复ReSend，From ReReceive");
                    }
                    if (message instanceof MapMessage) {
                        MapMessage mapMessage = (MapMessage) message;
                        Enumeration<?> enumeration = mapMessage.getMapNames();
                        System.err.println("收到的信息：");
                        while (enumeration.hasMoreElements()) {
                            String name = (String) enumeration.nextElement();
                            System.err.println("\n\t key = '" + name + "', value = '" + mapMessage.getObject(name) + "'");
                        }
                        response.setText("回复ReSend，From ReReceive(MapMessage)");
                    }
                    response.setJMSCorrelationID(message.getJMSCorrelationID());
                    replyProducer.send(message.getJMSReplyTo(), response);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
