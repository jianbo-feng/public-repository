# vertx测试应用
## 
```
说明：
1、vertx是异步；
2、springboot也支持异步（基于spring5的WebFlux），但主要是同步操作
```
## 环境配置
```
JDK：1.7++
vertx: 3.X
```
## 用例说明
### vertx-sample1
@[参考网页](https://vertxchina.github.io/vertx-translation-chinese/start/SimpleStart.html)
```
简单的启动一个httpServer，然后通过浏览器输入 http://localhost:7082/ 则可浏览测试信息：Hello world
另外：
可运行Gradle->Tasks->build->jar, 将项目打包成jar文件。
然后使用命令：java -jar sample1-1.0-SNAPSHOT.jar   运行，一样可以通过浏览器访问
```
### vertx-sample2
@[参考网页](https://blog.csdn.net/king_kgh/article/details/84894599)
```
Vertx访问MySQL
1、MySQL是安装在Docker上的MySQL55
2、访问MySQL时需要注意版本以及对应的驱动类
3、默认连接池使用c3p0
```
### vertx-sample3
@[参考](https://blog.csdn.net/king_kgh/article/details/80848571)
```
Vertx-web 开发测试
1、路由示例测试（Router）
```
### vertx-sample4
@[参考](https://github.com/vert-x3/vertx-examples/tree/master/camel-bridge-examples)
```
1、事件总线（EventBus）
```
### vertx-sample5
```

```
### vertx-sample6
```

```
### vertx-sample7
```

```
### vertx-sample8
```

```


