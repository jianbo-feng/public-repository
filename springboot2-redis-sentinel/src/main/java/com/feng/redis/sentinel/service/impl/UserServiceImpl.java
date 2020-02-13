package com.feng.redis.sentinel.service.impl;

import com.feng.redis.sentinel.entity.User;
import com.feng.redis.sentinel.repository.UserRepository;
import com.feng.redis.sentinel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends AbstractBaseService<User, String> implements UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        Iterable<User> iterable = userRepository.findAll();
        iterable.forEach(c -> list.add(c));
        return list;
    }
}
