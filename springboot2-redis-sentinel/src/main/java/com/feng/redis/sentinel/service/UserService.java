package com.feng.redis.sentinel.service;

import com.feng.redis.sentinel.entity.User;

import java.util.List;

/**
 * 用户信息
 */
public interface UserService extends BaseService<User, String> {

    List<User> getAll();
}
