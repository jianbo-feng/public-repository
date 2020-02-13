package com.feng.redis.controller;

import com.alibaba.fastjson.JSONObject;
import com.feng.redis.entity.User;
import com.feng.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Resource
    private RedisUtil redisUtil;

    //添加
    @GetMapping("save")
    public void save(){
        stringRedisTemplate.opsForValue().set("a", "test");
    }

    //获取
    @GetMapping("get")
    public String get(){
        return stringRedisTemplate.opsForValue().get("a");
    }

    @GetMapping("/saveList")
    public JSONObject saveList() {
        JSONObject result = new JSONObject();
        List<User> users = new ArrayList<>();
        users.add(new User(UUID.randomUUID().toString(), "张三", 20));
        users.add(new User(UUID.randomUUID().toString(), "里斯", 22));
        redisTemplate.opsForList().rightPushAll("users", users);
        return result;
    }

    @GetMapping("/getList")
    public JSONObject getList() {
        JSONObject result = new JSONObject();
        result.put("total", redisUtil.lGetListSize("users"));
        result.put("data", redisUtil.lGet("users", 0, -1)); //获取所有
        return result;
    }
}
