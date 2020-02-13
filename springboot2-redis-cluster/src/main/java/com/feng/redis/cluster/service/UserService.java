package com.feng.redis.cluster.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.feng.redis.cluster.entity.User;

/**
 * 用户信息
 */
public interface UserService {

    JSONObject save(User user);

    JSONObject get(String id);

    JSONObject all();
}
