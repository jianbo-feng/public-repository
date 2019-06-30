# SpringBoot + RabbitMQ

## 简介
```
Exchange 类型
Exchange分发消息时根据类型的不同分发策略有区别，目前共四种类型：direct、fanout、topic、headers 。只说前三种模式。

1.Direct模式

    消息中的路由键（routing key）如果和 Binding 中的 binding key 一致， 交换器就将消息发到对应的队列中。路由键与队列名完全匹配

2.Topic模式

    topic 交换器通过模式匹配分配消息的路由键属性，将路由键和某个模式进行匹配，此时队列需要绑定到一个模式上。
    它将路由键和绑定键的字符串切分成单词，这些单词之间用点隔开。
    它同样也会识别两个通配符：符号“#”和符号“*”。#匹配0个或多个单词，*匹配一个单词。

3.Fanout模式

    每个发到 fanout 类型交换器的消息都会分到所有绑定的队列上去。
    fanout 交换器不处理路由键，只是简单的将队列绑定到交换器上，每个发送到交换器的消息都会被转发到与该交换器绑定的所有队列上。
    很像子网广播，每台子网内的主机都获得了一份复制的消息。fanout 类型转发消息是最快的。

```

## 组件版本说明
```
1、SpringBoot 2.0.4 以及 2.1.6 都能正常运行
2、spring-boot-starter-amqp 跟随SpringBoot版本
```

## 项目运行说明
```
1、先确保已安装了activemq并能正常访问（不管是Docker方式还是非Docker方式）
2、activemq的默认账户及密码均为guest, 若设置了其他账户和密码，请在application.yml中修改
3、activemq的端口是否为默认端口（是否修改为其他端口以及是否被防火墙拦截等等，或者Docker映射的原因等等），不一致请修改为相应端口
```
