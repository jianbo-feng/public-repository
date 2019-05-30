package com.feng.swaggerdemo.controller.api;

import com.feng.swaggerdemo.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@Api(value = "/api/user", tags = "用户信息接口")
public class UserApiController {

    @GetMapping("get")
    @ApiOperation(value = "获得用户信息", notes = "根据用户名获得用户信息")
    public User get(@RequestParam String name) {
        return new User(UUID.randomUUID().toString(), name, "123456", 0);
    }

    @PostMapping("save")
    @ApiOperation(value = "保存用户信息", notes = "保存用户信息，根据用户名")
    public User save(@RequestParam String name) {
        return new User(UUID.randomUUID().toString(), name, "123456", 0);
    }

}
