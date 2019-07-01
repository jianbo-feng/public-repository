# SpringBoot2.1.6 + RocketMQ(v: 4.5.1)

## Module说明
```
1、rocket-bean 存放JavaBean或实体类
2、rocket-consumer rocket消息消费者
3、rocket-producer rocket消息生产者
```

## 项目启动
```
1、先检查生产者、消费者是否占用了端口
2、先启动消费者、再启动生产者，这样能及时让消费者监听器读取消息
```

## 附：windows10安装rocketmq
```
1.下载

RocketMQ下载链接：
http://mirror.bit.edu.cn/apache/rocketmq/4.5.1/rocketmq-all-4.5.1-bin-release.zip

参考文档：
https://www.jianshu.com/p/4a275e779afa

解压后路径：
C:\work\servers\rocketmq-all-4.5.1

2. 配置
    2.1 系统环境变量配置
        变量名：ROCKETMQ_HOME
        变量值：MQ解压路径\MQ文件夹名
    2.2重启服务器（需要重启电脑或服务器）

3. 启动

3.1 启动NAMESERVER
    Cmd命令框执行进入至‘MQ文件夹\bin’下，然后执行‘start mqnamesrv.cmd’，启动NAMESERVER。成功后会弹出提示框，此框勿关闭。

3.2 启动BROKER
    Cmd命令框执行进入至‘MQ文件夹\bin’下，然后执行‘start mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true’，启动BROKER。成功后会弹出提示框，此框勿关闭。
    假如弹出提示框提示‘错误: 找不到或无法加载主类 xxxxxx’。打开runbroker.cmd，然后将‘%CLASSPATH%’加上英文双引号。保存并重新执行start语句。
    set "JAVA_OPT=%JAVA_OPT% -server -Xms2g -Xmx2g -Xmn1g"
    set "JAVA_OPT=%JAVA_OPT% -XX:+UseG1GC -XX:G1HeapRegionSize=16m -XX:G1ReservePercent=25 -XX:InitiatingHeapOccupancyPercent=30 -XX:SoftRefLRUPolicyMSPerMB=0 -XX:SurvivorRatio=8"
    set "JAVA_OPT=%JAVA_OPT% -verbose:gc -Xloggc:%USERPROFILE%\mq_gc.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCApplicationStoppedTime -XX:+PrintAdaptiveSizePolicy"
    set "JAVA_OPT=%JAVA_OPT% -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=5 -XX:GCLogFileSize=30m"
    set "JAVA_OPT=%JAVA_OPT% -XX:-OmitStackTraceInFastThrow"
    set "JAVA_OPT=%JAVA_OPT% -XX:+AlwaysPreTouch"
    set "JAVA_OPT=%JAVA_OPT% -XX:MaxDirectMemorySize=15g"
    set "JAVA_OPT=%JAVA_OPT% -XX:-UseLargePages -XX:-UseBiasedLocking"
    set "JAVA_OPT=%JAVA_OPT% -Djava.ext.dirs=%BASE_DIR%lib"
    set "JAVA_OPT=%JAVA_OPT% -cp "%CLASSPATH%""
三. RocketMQ插件部署

1. 下载

    地址：https://github.com/apache/rocketmq-externals.git
    下载完成之后，进入‘rocketmq-externals\rocketmq-console\src\main\resources’文件夹，打开‘application.properties’进行配置。
    server.contextPath=
    server.port=8081

    ### SSL setting
    #server.ssl.key-store=classpath:rmqcngkeystore.jks
    #server.ssl.key-store-password=rocketmq
    #server.ssl.keyStoreType=PKCS12
    #server.ssl.keyAlias=rmqcngkey

    #spring.application.index=true
    spring.application.name=rocketmq-console
    spring.http.encoding.charset=UTF-8
    spring.http.encoding.enabled=true
    spring.http.encoding.force=true
    logging.config=classpath:logback.xml
    #if this value is empty,use env value rocketmq.config.namesrvAddr  NAMESRV_ADDR | now, you can set it in ops page.default localhost:9876
    rocketmq.config.namesrvAddr=127.0.0.1:9876
    #if you use rocketmq version < 3.5.8, rocketmq.config.isVIPChannel should be false.default true
    rocketmq.config.isVIPChannel=
    #rocketmq-console's data path:dashboard/monitor
    rocketmq.config.dataPath=/tmp/rocketmq-console/data
    #set it false if you don't want use dashboard.default true
    rocketmq.config.enableDashBoardCollect=true
    #set the message track trace topic if you don't want use the default one
    rocketmq.config.msgTrackTopicName=
    rocketmq.config.ticketKey=ticket

    #Must create userInfo file: ${rocketmq.config.dataPath}/users.properties if the login is required
    rocketmq.config.loginRequired=false
2. 编译启动
    进入‘\rocketmq-externals\rocketmq-console’文件夹，执行‘mvn clean package -Dmaven.test.skip=true’，编译生成。
    编译成功之后，Cmd进入‘target’文件夹，执行‘java -jar rocketmq-console-ng-1.0.0.jar’，启动‘rocketmq-console-ng-1.0.0.jar’。3.测试        
    浏览器中输入‘127.0.0.1：配置端口’，成功后即可查看。
    http://localhost:8081/#/
```
