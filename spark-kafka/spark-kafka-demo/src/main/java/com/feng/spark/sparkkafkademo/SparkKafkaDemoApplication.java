package com.feng.spark.sparkkafkademo;

import com.feng.spark.base.EnableSparkServer;
import com.feng.spark.base.KafkaStreamBuilder;
import com.feng.spark.base.config.KafkaProperties;
import com.feng.spark.base.config.MongodbProperties;
import com.feng.spark.base.config.SparkProperties;
import com.feng.spark.base.util.MongodbWriter;
import com.feng.spark.sparkkafkademo.entity.OrderEvent;
import com.feng.spark.sparkkafkademo.entity.TotalProducts;
import com.feng.spark.sparkkafkademo.processor.DemoProcessor;
import com.mongodb.BasicDBObject;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import scala.Tuple2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EnableSparkServer
@ComponentScan
//@SpringBootApplication
public class SparkKafkaDemoApplication implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(SparkKafkaDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SparkKafkaDemoApplication.class, args);
    }



    @Autowired
    protected KafkaProperties kafkaProperties;

    @Autowired
    protected JavaStreamingContext jssc;

    @Autowired
    protected SparkProperties sparkProperties;

    @Autowired
    private MongodbProperties mongodbProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {

//		JavaDStream<OrderEvent> orderDataStream = (JavaDStream<OrderEvent>) (new KafkaStreamBuilder(jssc,
//				sparkProperties, kafkaProperties)).build();


        MongodbWriter<TotalProducts> mw = new MongodbWriter<TotalProducts>("com.ai.demoapp.entity.TotalProducts",
                mongodbProperties);



        /*
         * 获取输入流
         */
        @SuppressWarnings("unchecked")
        JavaDStream<OrderEvent> orderDataStream = (JavaDStream<OrderEvent>) (new KafkaStreamBuilder())
                .setJssc(jssc)
                .setKafkaProperties(kafkaProperties)
                .setSparkProperties(sparkProperties)
                .build();

        orderDataStream.cache();

        /*
         * 设置初始化状态
         */
        List<Tuple2<String, Long>> tps = new ArrayList<Tuple2<String, Long>>();

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("recordDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        List<TotalProducts> prodList = mw.read(searchQuery);
        for (TotalProducts prod : prodList) {

            tps.add(new Tuple2<>(prod.getProductId(), prod.getTotalCount()));

        }

        JavaPairRDD<String, Long> initState = jssc.sparkContext().parallelizePairs(tps);




        /*
         * 实时数据处理
         */

        DemoProcessor demoProcessor = new DemoProcessor();

        JavaDStream<TotalProducts> productsDStream = demoProcessor.process(orderDataStream,initState);


        /*
         * 输出数据保存，这里是保存到mongodb
         */

        mw.save(productsDStream);

        /*
         *  启动上下文
         *
         */
        jssc.start();
        jssc.awaitTermination();

    }
}
