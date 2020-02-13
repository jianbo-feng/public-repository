package com.songguoliang.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/test/demo")
    public JSONObject demo() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "test-user");
        jsonObject.put("age", "23");
        return jsonObject;
    }
}
