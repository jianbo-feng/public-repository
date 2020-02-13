package com.feng.datasource.multiple.dynamic.mybatis.controller;

import com.feng.datasource.multiple.dynamic.mybatis.entity.User;
import com.feng.datasource.multiple.dynamic.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/demo")
@RestController
public class DemoController {

    @Autowired
    private UserService userService;

    @GetMapping("/allUser")
    public List<User> allUser() {
//        userService.testTransactional();
        return userService.getAll();
    }
}
