package com.feng.redis.cluster.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.feng.redis.cluster.entity.User;
import com.feng.redis.cluster.service.UserService;
import com.feng.redis.cluster.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

//    @Resource
//    private RedisUtil redisUtil;

    @Override
    public JSONObject save(User user) {
        JSONObject vo = new JSONObject();
        try {
            vo.put("data", RedisUtil.setString("user", JSON.toJSONString(user), 60));
        }
        catch (Exception e) {
            vo.put("error", e.getMessage());
        }

        vo.put("code", 200);
        return vo;
    }

    @Override
    public JSONObject get(String id) {
        JSONObject vo = new JSONObject();
        try {
            vo.put("data", RedisUtil.getString("user"));
        }
        catch (Exception e) {
            vo.put("error", e.getMessage());
        }

        vo.put("code", 200);
        return vo;
    }

    @Override
    public JSONObject all() {
        JSONObject vo = new JSONObject();
        try {
            vo.put("data", RedisUtil.getString("user"));
        }
        catch (Exception e) {
            vo.put("error", e.getMessage());
        }

        vo.put("code", 200);
        return vo;
    }
}
