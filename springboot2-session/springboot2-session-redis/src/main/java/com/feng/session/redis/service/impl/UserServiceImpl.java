package com.feng.session.redis.service.impl;

import com.feng.session.redis.entity.User;
import com.feng.session.redis.repository.UserRepository;
import com.feng.session.redis.service.UserService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Feng
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return Lists.newArrayList(userRepository.findAll());
    }
}
