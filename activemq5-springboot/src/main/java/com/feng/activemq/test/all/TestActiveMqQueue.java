package com.feng.activemq.test.all;

import javax.jms.JMSException;

public class TestActiveMqQueue {
    public static void main(String[] args) throws InterruptedException, JMSException
    {
        ActivemqQueueProducer producer = new ActivemqQueueProducer();
        ActivemqQueueConsumer consumer = new ActivemqQueueConsumer("1");
        ActivemqQueueConsumerAsyn consumer1 = new ActivemqQueueConsumerAsyn("2");
        producer.initialize();


        Thread.sleep(500);
        for(int i=0;i<5;i++)
        {
            producer.sendText("Hello, world!"+i);
        }

        //producer.submit();//如果开启事务需使用
        // producer.close();
        System.out.println("consumer1开始监听");
        //consumer.recive();
        //consumer.close();
        System.out.println("consumer1接收完毕！");


        for(int i=0;i<10;i++)
        {
            producer.sendText("Hello, world!"+i);
        }
        //producer.submit();//如果开启事务需使用
        System.out.println("consumer2开始监听");
        consumer1.recive();
        System.out.println("consumer2接收完毕！");

    }
}

