package com.feng.task.config;

import net.javacrumbs.shedlock.provider.redis.jedis.JedisLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPool;

@Configuration
@EnableSchedulerLock(defaultLockAtMostFor = "PT60M")
public class ShedLockConfig {

    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private Integer redisPort;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.timeout}")
    private Integer timeout;
    @Value("${spring.redis.database}")
    private int database;

    @Bean
    public JedisLockProvider lockProvider() {
        JedisPool jedisPool;
        if (StringUtils.isEmpty(password)) {
            jedisPool = new JedisPool(redisHost, redisPort);
        } else {
            GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
            jedisPool = new JedisPool(genericObjectPoolConfig, redisHost, redisPort, timeout,
                    password, database);
        }
        return new JedisLockProvider(jedisPool);
    }
}
