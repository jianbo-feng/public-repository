package com.feng.redis.cluster.controller;


import com.alibaba.fastjson.JSONObject;
import com.feng.redis.cluster.entity.User;
import com.feng.redis.cluster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserService userService;

    @GetMapping("save")
    public JSONObject save() {
        return userService.save(new User(UUID.randomUUID().toString(), "李虎", 12));
    }

    @GetMapping("all")
    public JSONObject all() {
        return userService.all();
    }
}
