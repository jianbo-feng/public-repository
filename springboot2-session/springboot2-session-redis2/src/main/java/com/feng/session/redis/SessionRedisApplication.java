package com.feng.session.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisRepositories(basePackages = "com.feng.session.redis.repository")
@EnableCaching
@EnableRedisHttpSession
@SpringBootApplication
public class SessionRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SessionRedisApplication.class, args);
    }

}
