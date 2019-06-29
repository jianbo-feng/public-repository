# SpringBoot2.0.4 + ActiveMQ5.15.9

## _特别说明_ 
```
1、本来打算使用SpringBoot2.1.6和ActiveMQ5.15.9 做一个示例，但是发现配置起来组件的版本不能匹配，
故使用SpringBoot2.0.4版本
2、若不使用SpringBoot2.1.6，直接使用Java的组件（例如最新activemq-all、activemq-pool等），
就可以直接连接访问ActiveMQ5.15.9 版本也能一致
```

## 端口
```
8161：后台管理系统，
61616：给java用的tcp端口
```


## Web管理平台
```
http://localhost:8161/
默认用户名：admin
默认用户名密码：admin
```

## 基本命令
```
1、查看版本
activemq --version
2、启动
activemq start
3、停止
activemq stop
4、重启
activemq restart
5、查看状态
activemq status
```

## 当前示例说明
```
配置文件application.yml中
1、spring.jml.pub-sub-domain=false , 表示消息模式为queue（即队列）
    http://localhost:9080/jms/activemq/queue?msg=hello，
    消费端监听器（ConsumerQueue）能获得数据
2、spring.jml.pub-sub-domain=true , 表示消息模式为topic（即消息主题）
    http://localhost:9080/jms/activemq/topic?msg=hello，
    消费端监听器（ConsumerTopic）能获得数据    
3、JSON数据发送
    http://localhost:9080/jms/activemq/json?model=topic 消息主题模式发送
    http://localhost:9080/jms/activemq/json 队列模式发送
```