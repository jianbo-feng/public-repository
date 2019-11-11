package com.feng.session.redis.service;

import com.feng.session.redis.entity.User;

import java.util.List;

/**
 * 用户信息
 */
public interface UserService {

    List<User> getAll();
}
