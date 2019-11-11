package com.feng.session.redis.service.impl;

import com.feng.session.redis.entity.User;
import com.feng.session.redis.repository.UserRepository;
import com.feng.session.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<User> list = new ArrayList<>();
        Iterable<User> iterable = userRepository.findAll();
        iterable.forEach(c -> list.add(c));
        return list;
    }
}
