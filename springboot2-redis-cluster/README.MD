# Redis5集群
## 简介
```
Redis集群主要分主备(譬如常见的一主二备)、哨兵以及集群;
```
## 主备模式(redis.conf)
```
即一台主机(master)，两台备(slave)
主配置
    # 使得Redis服务器可以跨网络访问
    bind 0.0.0.0
    port 6739
    # 设置密码
    requirepass "123456"
    # 指定主服务器，注意：有关slaveof的配置只是配置从服务器，主服务器不需要配置
    # 主服务器密码，注意：有关slaveof的配置只是配置从服务器，主服务器不需要配置
    masterauth 123456
备配置
    # 使得Redis服务器可以跨网络访问
    bind 0.0.0.0
    port 6380
    # 设置密码
    requirepass "123456"
    # 指定主服务器，注意：有关slaveof的配置只是配置从服务器，主服务器不需要配置
    slaveof 127.0.0.1 6379
    # 主服务器密码，注意：有关slaveof的配置只是配置从服务器，主服务器不需要配置
    masterauth 123456
    
    # 使得Redis服务器可以跨网络访问
    bind 0.0.0.0
    port 6381
    # 设置密码
    requirepass "123456"
    # 指定主服务器，注意：有关slaveof的配置只是配置从服务器，主服务器不需要配置
    slaveof 127.0.0.1 6379
    # 主服务器密码，注意：有关slaveof的配置只是配置从服务器，主服务器不需要配置
    masterauth 123456
```
## 哨兵模式(sentinel.conf)
```
添加哨兵配置
    # 禁止保护模式
    protected-mode no
    # 配置监听的主服务器，这里sentinel monitor代表监控，mymaster代表服务器的名称，可以自定义，192.168.11.128代表监控的主服务器，6379代表端口，2代表只有两个或两个以上的哨兵认为主服务器不可用的时候，才会进行failover操作。
    sentinel monitor mymaster 127.0.0.1 6379 2
    # sentinel author-pass定义服务的密码，mymaster是服务名称，123456是Redis服务器密码
    # sentinel auth-pass <master-name> <password>
    sentinel auth-pass mymaster 123456
```
## 集群模式(略)
```
由于此种模式不支持同时获取多个key，因为这种模式是根据一定的hash规则将key存于集群中的不同节点，所以同时取多个key值不支持；
这种模式适合大型系统或项目，只取单个值；一般项目不太适合
```
