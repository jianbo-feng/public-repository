package com.feng.swaggerdemo.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "/api/test", tags = "测试接口")
@RestController
@RequestMapping("/api/test")
public class TestApiController {

    @ApiOperation(value = "问候接口", notes = "提供用户名，进行问候", httpMethod = "GET")
    @GetMapping("hello")
    public String hello(String name) {
        return "hello : " + name;
    }
}
