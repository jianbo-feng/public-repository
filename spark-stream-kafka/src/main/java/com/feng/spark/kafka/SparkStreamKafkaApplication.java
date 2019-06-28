package com.feng.spark.kafka;

import com.feng.spark.kafka.sparkstream.executor.SparkKafkaStreamExecutor;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;

@SpringBootApplication
public class SparkStreamKafkaApplication implements ApplicationListener<ContextRefreshedEvent> {

    public static void main(String[] args) {
        SpringApplication.run(SparkStreamKafkaApplication.class, args);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext ac = event.getApplicationContext();
        SparkKafkaStreamExecutor sparkKafkaStreamExecutor= ac.getBean(SparkKafkaStreamExecutor.class);
        Thread thread = new Thread(sparkKafkaStreamExecutor);
        thread.start();
    }

    //将Gson划归为spring管理
    @Bean
    public Gson gson() {
        return new Gson();
    }
}
