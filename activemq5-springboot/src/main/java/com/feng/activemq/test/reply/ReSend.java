package com.feng.activemq.test.reply;

import com.alibaba.fastjson.JSON;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.thymeleaf.util.DateUtils;

import javax.jms.*;
import java.util.Date;
import java.util.UUID;

/**
 * Reply(应答模式)：发送端代码
 * @author
 * @date 2020/03/06
 * @see <a href="https://www.jianshu.com/p/6027cabd6ae5">参考</a>
 */
public class ReSend {

    public static void main(String... args) throws JMSException, InterruptedException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(Constants.ACTIVEMQ_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        //创建会话，用于发送和接收消息，而且是单线程的，支持事务；第一个参数是是否开启事务，第二参数表示应答模式
        //Session.AUTO_ACKNOWLEDGE。当客户成功的从receive方法返回的时候，或者从MessageListener.onMessage方法成功返回的时候，会话自动确认客户收到的消息。
        // Session.CLIENT_ACKNOWLEDGE。 客户通过消息的acknowledge方法确认消息。需要注意的是，在这种模式中，确认是在会话层上进行：确认一个被消费的消息将自动确认所有已被会话消 费的消息。例如，如果一个消息消费者消费了10个消息，然后确认第5个消息，那么所有10个消息都被确认。
        // JMS消息只有在被确认之后，才认为已经被成功地消费了。消息的成功消费通常包含三个阶段：客户接收消息、客户处理消息和消息被确认。
        //Session.DUPS_ACKNOWLEDGE。 该选择只是会话迟钝第确认消息的提交。如果JMS provider失败，那么可能会导致一些重复的消息。如果是重复的消息，那么JMS provider必须把消息头的JMSRedelivered字段设置为true
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        Destination adminQueue = session.createQueue(Constants.QUEUE_NAME);

        // 创建生产者发送消息
        MessageProducer producer = session.createProducer(adminQueue);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        // 创建一个临时的通道(即临时队列)，并且创建消费者(用于监听临时通道接收到的消息)
        Destination tempDest = session.createTemporaryQueue();
        MessageConsumer responseConsumer = session.createConsumer(tempDest);

        // 设置临时消息的一个监听器
        responseConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    String replyContent = "";
                    if (message instanceof TextMessage) {
                        replyContent = ((TextMessage) message).getText();
                    }
                    else if (message instanceof MapMessage) {
                        replyContent = JSON.toJSONString(((MapMessage) message));
                    }
                    else if (message instanceof ObjectMessage) {
                        replyContent = ((ObjectMessage) message).getObject().toString();
                    }
                    else if (message instanceof BytesMessage) {
                        replyContent = ((BytesMessage) message).toString();
                    }
                    else {
                        replyContent = message.toString();
                    }

                    System.out.println("收到回复的内容：" + replyContent + " \n\t 时间为： " + new Date());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        // 通过临时通道发送消息
        TextMessage textMessage = session.createTextMessage();
        textMessage.setText("来自ReSend的消息：" + System.currentTimeMillis());

        // 设置消息头，使得回复的消息可以定位到刚才的临时通道(tempDest)
        textMessage.setJMSReplyTo(tempDest);

        // 在消息头部设置一个标识，好像仅仅是用于业务上的判断，并不影响消息的应答
        String correlationId = UUID.randomUUID().toString();
        System.err.println("correlationId => " + correlationId);
        textMessage.setJMSCorrelationID(correlationId);
        producer.send(textMessage);

        Thread.sleep(2000L);

        // 再发送一个消息(也使用临时通道)
        MapMessage mapMessage = session.createMapMessage();
        mapMessage.setString("surname", "张");
        mapMessage.setString("lastname", "三");
        mapMessage.setInt("age", 23);
        mapMessage.setJMSReplyTo(tempDest);
        mapMessage.setJMSCorrelationID(correlationId);
        producer.send(mapMessage);
    }
}
