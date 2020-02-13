# Springboot2 集成Redis(sentinel)
##
```
1、使用jedis不用lettuce
2、yml配置
    spring:
      application:
        name: springboot2-redis-cluster
      redis:
        sentinel:
          master: mymaster
          nodes: 127.0.0.1:26379,127.0.0.1:26382,127.0.0.1:26383
    #    password:
        database: 0
        timeout: 0
        jedis:
          pool:
            max-active: 8
            max-wait: -1
            max-idle: 8
            min-idle: 0
      data:
        redis:
          repositories:
            enabled: true
3、实例化repositories, 指定目录
    @EnableRedisRepositories(basePackages = {"com.feng.redis.sentinel.repository"})
```
