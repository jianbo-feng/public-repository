package com.feng.redis.cluster.sentinel;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 测试哨兵模式
 */
public class TestSentinels {

    public static void main(String... args) throws Exception {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10);
        jedisPoolConfig.setMaxIdle(5);
        jedisPoolConfig.setMinIdle(5);
        // 哨兵信息
        Set<String> sentinels = new HashSet<>(Arrays.asList("127.0.0.1:26379",
                "127.0.0.1:26382", "127.0.0.1:26383"));
        // 创建连接池
//        JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels,jedisPoolConfig,"");
        JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels,jedisPoolConfig);
        // 获取客户端
        Jedis jedis = pool.getResource();
        jedis.set("mykey", "myvalue");
        String value = jedis.get("mykey");
        System.out.println(value);
    }
}
