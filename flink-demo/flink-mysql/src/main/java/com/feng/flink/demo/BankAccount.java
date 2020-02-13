package com.feng.flink.demo;


import com.alibaba.fastjson.JSON;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.io.jdbc.JDBCAppendTableSink;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer09;
import org.apache.flink.types.Row;

import java.util.Properties;

/**
 * Flink操作从kafka中获取消息，然后对MySQL表进行操作
 * @reference https://blog.csdn.net/Mathieu66/article/details/88975963
 */
public class BankAccount {

    // 数据类型，按顺序，与数据库字段保持一致
    private static final TypeInformation[] FIELD_TYPES = new TypeInformation[] {
            BasicTypeInfo.STRING_TYPE_INFO,
            BasicTypeInfo.INT_TYPE_INFO,
            BasicTypeInfo.STRING_TYPE_INFO,
            BasicTypeInfo.LONG_TYPE_INFO
    };

    public static void main(String... args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 设置检查点
        env.enableCheckpointing(5000);
        // 设置eventTime
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        // Kafka消费者参数
        Properties consumerProperties = new Properties();
        consumerProperties.setProperty("bootstrap.servers", "localhost:9092");
        consumerProperties.setProperty("group.id", "test");
        consumerProperties.setProperty("enable.auto.commit", "true");

        FlinkKafkaConsumer09 consumer = new FlinkKafkaConsumer09<String>("topicA",  new SimpleStringSchema(), consumerProperties);
        // 接收kafka中的参数
        DataStream<String> kafkaData = env.addSource(consumer);
        // 将JSON字符串解析成Row对象
        DataStream<Row> accountLatest = kafkaData
                .map(x -> JSON.parseObject(x))
                .map(x -> Row.of(x.getString("name"),
                        x.getInteger("age"),
                        x.getString("account"),
                        System.currentTimeMillis()));
        // 拼接sql
        String upsertSql = "INSERT INTO bank_account (name,age,account,update_time) values (?,?,?,?) ON DUPLICATE KEY UPDATE " +
                "age=VALUES(age),account=VALUES(account),update_time=VALUES(update_time)";

        // 写出到数据库 （CRUD都可以、主要看sql是什么，感兴趣的可以自己测试）
        JDBCAppendTableSink sink = JDBCAppendTableSink.builder()
                .setDrivername("com.mysql.jdbc.Driver")
                .setDBUrl("jdbc:mysql://localhost:3306/testdb?characterEncoding=utf8&useSSL=true")
                .setUsername("root")
                .setPassword("123456")
                .setQuery(upsertSql)
                .setParameterTypes(FIELD_TYPES)
                .build();

        sink.emitDataStream(accountLatest);

        // 执行
        env.execute("bank account");
    }
}
