package com.feng.sofa.sample.service.impl;

import com.feng.sofa.sample.service.HelloService;

import static java.lang.System.err;

public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String string) {
        err.println("Server recive : " + string);
        return "hello " + string + " ! ";
    }
}
