package com.feng.redis.sentinel.controller;

import com.alibaba.fastjson.JSONObject;
import com.feng.redis.sentinel.entity.User;
import com.feng.redis.sentinel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("save")
    public JSONObject save() {
        JSONObject vo = new JSONObject();
        try {
            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setAge(22);
            user.setName("test001");
            userService.save(user);
            vo.put("code", 200);
        }
        catch (Exception e) {
            e.printStackTrace();
            vo.put("code", 500);
            vo.put("error", e.getMessage());
        }
        return vo;
    }

    @GetMapping("all")
    public JSONObject all() {
        JSONObject vo = new JSONObject();
        try {
            vo.put("data", userService.findAll());
            vo.put("all", userService.getAll());
            vo.put("total", userService.count());
            vo.put("code", 200);
        }
        catch (Exception e) {
            e.printStackTrace();
            vo.put("code", 500);
            vo.put("error", e.getMessage());
        }
        return vo;
    }

}
