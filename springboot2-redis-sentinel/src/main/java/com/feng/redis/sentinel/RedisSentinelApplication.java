package com.feng.redis.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableRedisRepositories(basePackages = {"com.feng.redis.sentinel.repository"})
@SpringBootApplication
public class RedisSentinelApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisSentinelApplication.class, args);
    }

}
